package com.example.easyrpm.ui.buscar
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
import com.example.easyrpm.repository.ApiRepository
import kotlinx.coroutines.launch

class EncontrarHuActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvVacio: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encontrar_hu)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar  = findViewById(R.id.progressBar)
        tvVacio      = findViewById(R.id.tvVacio)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cargarDatos()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }

    private fun cargarDatos() {
        progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            val hus = ApiRepository.getHus()
            runOnUiThread {
                progressBar.visibility = View.GONE
                if (hus.isEmpty()) { tvVacio.visibility = View.VISIBLE; recyclerView.visibility = View.GONE }
                else { tvVacio.visibility = View.GONE; recyclerView.visibility = View.VISIBLE
                    recyclerView.adapter = EncontrarHuAdapter(hus) { confirmarBaja(it) } }
            }
        }
    }

    private fun confirmarBaja(hu: HU) {
        AlertDialog.Builder(this).setTitle("Dar de baja HU ${hu.sscc}").setMessage("Esta accion es irreversible.")
            .setPositiveButton("Eliminar") { _, _ ->
                lifecycleScope.launch {
                    val ok = ApiRepository.eliminarHu(hu.sscc)
                    runOnUiThread {
                        if (ok) { Toast.makeText(this@EncontrarHuActivity, "HU eliminada", Toast.LENGTH_SHORT).show(); cargarDatos() }
                        else Toast.makeText(this@EncontrarHuActivity, "Error al eliminar", Toast.LENGTH_SHORT).show()
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
        h.tvSscc.text = "SSCC: ${hu.sscc}"; h.tvMaterial.text = "Material: ${hu.material?.nombre ?: "-"}"
        h.tvPeso.text = "Peso: ${hu.peso} kg"; h.tvUbicacion.text = "Ubicacion: ${hu.ubicacion?.nombre ?: "Sin asignar"}"
        h.btnAsignar.text = "Dar de baja"; h.btnAsignar.setOnClickListener { onBaja(hu) }
    }
}