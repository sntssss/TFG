package com.example.easyrpm.ui.pendientes
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easyrpm.R
import com.example.easyrpm.model.HU
import com.example.easyrpm.model.Ubicacion
import com.example.easyrpm.repository.ApiRepository
import kotlinx.coroutines.launch

class HusPendientesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvVacio: TextView
    private var listaUbicaciones: List<Ubicacion> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hus_pendietes)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar  = findViewById(R.id.progressBar)
        tvVacio      = findViewById(R.id.tvVacio)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cargarDatos()
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }

    private fun cargarDatos() {
        progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            val hus = ApiRepository.getHusPendientes()
            listaUbicaciones = ApiRepository.getUbicaciones()
            runOnUiThread {
                progressBar.visibility = View.GONE
                if (hus.isEmpty()) {
                    tvVacio.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {
                    tvVacio.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    recyclerView.adapter = HusPendientesAdapter(hus) { mostrarDialogoUbicacion(it) }
                }
            }
        }
    }

    private fun mostrarDialogoUbicacion(hu: HU) {
        if (listaUbicaciones.isEmpty()) {
            Toast.makeText(this, "Sin ubicaciones disponibles", Toast.LENGTH_SHORT).show(); return
        }
        val spinner = Spinner(this)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            listaUbicaciones.map { it.nombre }).also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        val container = android.widget.LinearLayout(this)
        container.orientation = android.widget.LinearLayout.VERTICAL
        container.setPadding(80, 60, 80, 40)
        val label = android.widget.TextView(this)
        label.text = "Selecciona almacen:"
        label.textSize = 15f
        label.setPadding(0, 0, 0, 40)
        container.addView(label)
        container.addView(spinner)
                AlertDialog.Builder(this)
            .setTitle("Asignar almacen a HU ${hu.sscc}")
            .setView(container)
            .setPositiveButton("Asignar") { _, _ ->
                val ub = listaUbicaciones[spinner.selectedItemPosition]
                lifecycleScope.launch {
                    ApiRepository.asignarUbicacion(hu, ub)
                    runOnUiThread {
                        Toast.makeText(this@HusPendientesActivity,
                            "Ubicacion asignada correctamente", Toast.LENGTH_SHORT).show()
                        cargarDatos()
                    }
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}

class HusPendientesAdapter(private val hus: List<HU>, private val onAsignar: (HU) -> Unit)
    : RecyclerView.Adapter<HusPendientesAdapter.HuViewHolder>() {
    inner class HuViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvSscc: TextView = v.findViewById(R.id.tvSscc)
        val tvMaterial: TextView = v.findViewById(R.id.tvMaterial)
        val tvPeso: TextView = v.findViewById(R.id.tvPeso)
        val btnAsignar: Button = v.findViewById(R.id.btnAsignar)
    }
    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        HuViewHolder(LayoutInflater.from(p.context).inflate(R.layout.item_hu, p, false))
    override fun getItemCount() = hus.size
    override fun onBindViewHolder(h: HuViewHolder, i: Int) {
        val hu = hus[i]
        h.tvSscc.text = "SSCC: ${hu.sscc}"
        h.tvMaterial.text = "Material: ${hu.material?.nombre ?: "-"}"
        h.tvPeso.text = "Peso: ${hu.peso} kg"
        h.btnAsignar.setOnClickListener { onAsignar(hu) }
    }
}
