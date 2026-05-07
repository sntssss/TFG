package com.example.easyrpm.ui.ajustes
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.easyrpm.R
import com.example.easyrpm.ui.login.LoginActivity
import com.example.easyrpm.utils.SessionManager

class AjustesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val sm = SessionManager(this)
        findViewById<TextView>(R.id.tvNombreCompleto).text = sm.getNombre()
        findViewById<TextView>(R.id.tvTipoUsuario).text = sm.getRol()
        findViewById<TextView>(R.id.tvTipoUsuarioBadge).text = sm.getRol()
        findViewById<TextView>(R.id.tvDni).text = sm.getDni()

        findViewById<LinearLayout>(R.id.layoutContacto).setOnClickListener {
            mostrarContacto()
        }

        findViewById<Button>(R.id.btnCerrarSesion).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Cerrar sesion")
                .setMessage("Seguro que quieres salir?")
                .setPositiveButton("Si") { _, _ ->
                    sm.cerrarSesion()
                    startActivity(Intent(this, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }); finish()
                }
                .setNegativeButton("No", null).show()
        }
    }

    private fun mostrarContacto() {
        AlertDialog.Builder(this)
            .setTitle("Contacto")
            .setMessage("Si tienes alguna duda o problema, contacta con el equipo de soporte:\n\nsoporte@easyrpm.com")
            .setPositiveButton("Cerrar", null).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}
