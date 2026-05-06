package com.example.easyrpm.ui.recepcion
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.example.easyrpm.R
import com.example.easyrpm.model.HU
import com.example.easyrpm.model.Material
import com.example.easyrpm.repository.ApiRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.Calendar

class NuevaRecepcionActivity : AppCompatActivity() {
    private lateinit var etSscc: TextInputEditText
    private lateinit var etLote: TextInputEditText
    private lateinit var etPeso: TextInputEditText
    private lateinit var etFecha: TextInputEditText
    private lateinit var spinnerMaterial: Spinner
    private lateinit var btnRegistrar: Button
    private var listaMateriales: List<Material> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_recepcion)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        etSscc          = findViewById(R.id.etSscc)
        etLote          = findViewById(R.id.etLote)
        etPeso          = findViewById(R.id.etPeso)
        etFecha         = findViewById(R.id.etFechaCaducidad)
        spinnerMaterial = findViewById(R.id.spinnerMaterial)
        btnRegistrar    = findViewById(R.id.btnRegistrar)
        etFecha.setOnClickListener { mostrarCalendario() }
        cargarMateriales()
        btnRegistrar.setOnClickListener { registrarHU() }
    }

    private fun mostrarCalendario() {
        val cal = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, day ->
            etFecha.setText(String.format("%04d-%02d-%02d", year, month + 1, day))
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }

    private fun cargarMateriales() {
        lifecycleScope.launch {
            listaMateriales = ApiRepository.getMateriales()
            runOnUiThread {
                val nombres = listaMateriales.map { it.nombre }.ifEmpty { listOf("Sin productos disponibles") }
                spinnerMaterial.adapter = ArrayAdapter(this@NuevaRecepcionActivity,
                    android.R.layout.simple_spinner_item, nombres)
                    .also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
            }
        }
    }

    private fun registrarHU() {
        val sscc  = etSscc.text.toString().toLongOrNull()
        val lote  = etLote.text.toString().toLongOrNull()
        val peso  = etPeso.text.toString().toDoubleOrNull()
        val fecha = etFecha.text.toString()
        if (sscc == null || lote == null || peso == null || fecha.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        val material = listaMateriales.getOrNull(spinnerMaterial.selectedItemPosition)
        btnRegistrar.isEnabled = false
        lifecycleScope.launch {
            val error = ApiRepository.addHu(HU(sscc=sscc, lote=lote, peso=peso, fecha_caducidad=fecha, material=material))
            runOnUiThread {
                btnRegistrar.isEnabled = true
                if (error == null) {
                    Toast.makeText(this@NuevaRecepcionActivity, "HU registrada correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@NuevaRecepcionActivity, error ?: "Error al registrar", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}