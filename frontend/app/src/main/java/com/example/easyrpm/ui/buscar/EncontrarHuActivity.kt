package com.example.easyrpm.ui.buscar
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easyrpm.R
import com.example.easyrpm.model.HU
import com.example.easyrpm.model.Material
import com.example.easyrpm.model.Ubicacion
import com.example.easyrpm.repository.ApiRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class EncontrarHuActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvVacio: TextView
    private lateinit var etBuscar: TextInputEditText
    private lateinit var spinnerUbicacion: Spinner
    private lateinit var spinnerMaterial: Spinner

    private var todasHus: List<HU> = emptyList()
    private var listaUbicaciones: List<Ubicacion> = emptyList()
    private var listaMateriales: List<Material> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encontrar_hu)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView     = findViewById(R.id.recyclerView)
        progressBar      = findViewById(R.id.progressBar)
        tvVacio          = findViewById(R.id.tvVacio)
        etBuscar         = findViewById(R.id.etBuscar)
        spinnerUbicacion = findViewById(R.id.spinnerFiltroUbicacion)
        spinnerMaterial  = findViewById(R.id.spinnerFiltroMaterial)
        recyclerView.layoutManager = LinearLayoutManager(this)

        etBuscar.addTextChangedListener { aplicarFiltros() }
        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { aplicarFiltros() }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        spinnerUbicacion.onItemSelectedListener = listener
        spinnerMaterial.onItemSelectedListener = listener
        cargarDatos()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }

    private fun cargarDatos() {
        progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            todasHus        = ApiRepository.getHus()
            listaUbicaciones = ApiRepository.getUbicaciones()
            listaMateriales  = ApiRepository.getMateriales()
            runOnUiThread {
                progressBar.visibility = View.GONE
                // Filtro ubicacion
                val ubicNombres = mutableListOf("Todas las ubicaciones") + listaUbicaciones.map { it.nombre }
                spinnerUbicacion.adapter = ArrayAdapter(this@EncontrarHuActivity, android.R.layout.simple_spinner_item, ubicNombres).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
                // Filtro material
                val matNombres = mutableListOf("Todos los materiales") + listaMateriales.map { it.nombre }
                spinnerMaterial.adapter = ArrayAdapter(this@EncontrarHuActivity, android.R.layout.simple_spinner_item, matNombres).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
                aplicarFiltros()
            }
        }
    }

    private fun aplicarFiltros() {
        val texto = etBuscar.text.toString().trim().lowercase()
        val ubicSel = spinnerUbicacion.selectedItemPosition - 1 // -1 = todas
        val matSel  = spinnerMaterial.selectedItemPosition - 1

        val husFiltradas = todasHus.filter { hu ->
            val matchTexto = texto.isEmpty() || hu.sscc.toString().contains(texto) || (hu.material?.nombre ?: "").lowercase().contains(texto)
            val matchUbic = ubicSel < 0 || hu.ubicacion?.nombre == listaUbicaciones.getOrNull(ubicSel)?.nombre
            val matchMat = matSel < 0 || hu.material?.nombre == listaMateriales.getOrNull(matSel)?.nombre
            matchTexto && matchUbic && matchMat
        }

        if (husFiltradas.isEmpty()) {
            tvVacio.visibility = View.VISIBLE; recyclerView.visibility = View.GONE
        } else {
            tvVacio.visibility = View.GONE; recyclerView.visibility = View.VISIBLE
            recyclerView.adapter = EncontrarHuAdapter(husFiltradas) { confirmarBaja(it) }
        }
    }

    private fun confirmarBaja(hu: HU) {
        AlertDialog.Builder(this).setTitle("Dar de baja HU ${hu.sscc}").setMessage("Esta accion es irreversible.")
            .setPositiveButton("Eliminar") { _, _ ->
                lifecycleScope.launch {
                    ApiRepository.eliminarHu(hu.sscc)
                    runOnUiThread {
                        Toast.makeText(this@EncontrarHuActivity, "HU eliminada", Toast.LENGTH_SHORT).show()
                        cargarDatos()
                    }
                }
            }.setNegativeButton("Cancelar", null).show()
    }
}

class EncontrarHuAdapter(private val hus: List<HU>, private val onBaja: (HU)->Unit)
    : RecyclerView.Adapter<EncontrarHuAdapter.HuViewHolder>() {
    inner class HuViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvSscc: TextView = v.findViewById(R.id.tvSscc)
        val tvMaterial: TextView = v.findViewById(R.id.tvMaterial)
        val tvPeso: TextView = v.findViewById(R.id.tvPeso)
        val tvUbicacion: TextView = v.findViewById(R.id.tvUbicacion)
        val btnAsignar: Button = v.findViewById(R.id.btnAsignar)
    }
    override fun onCreateViewHolder(p: ViewGroup, t: Int) = HuViewHolder(LayoutInflater.from(p.context).inflate(R.layout.item_hu, p, false))
    override fun getItemCount() = hus.size
    override fun onBindViewHolder(h: HuViewHolder, i: Int) {
        val hu = hus[i]
        h.tvSscc.text = "SSCC: ${hu.sscc}"
        h.tvMaterial.text = "Material: ${hu.material?.nombre ?: "-"}"
        h.tvPeso.text = "Peso: ${hu.peso} kg"
        h.tvUbicacion.text = "Ubicacion: ${hu.ubicacion?.nombre ?: "Sin asignar"}"
        h.btnAsignar.text = "Dar de baja"
        h.btnAsignar.setOnClickListener { onBaja(hu) }
    }
}
