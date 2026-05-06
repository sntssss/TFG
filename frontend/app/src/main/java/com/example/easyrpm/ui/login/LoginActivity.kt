package com.example.easyrpm.ui.login
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.easyrpm.R
import com.example.easyrpm.repository.ApiRepository
import com.example.easyrpm.ui.main.MainActivity
import com.example.easyrpm.utils.SessionManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var etUsuario: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var tvError: TextView
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sessionManager = SessionManager(this)
        if (sessionManager.estaLogado()) { irAlMenu(); return }
        etUsuario = findViewById(R.id.etUsuario)
        btnLogin  = findViewById(R.id.btnLogin)
        tvError   = findViewById(R.id.tvError)
        btnLogin.setOnClickListener {
            intentarLogin(etUsuario.text.toString().trim())
        }
    }

    private fun intentarLogin(dni: String) {
        if (dni.isEmpty()) {
            tvError.text = "Introduce tu DNI"; tvError.visibility = View.VISIBLE; return
        }
        btnLogin.isEnabled = false; tvError.visibility = View.GONE
        lifecycleScope.launch {
            val usuario = ApiRepository.login(dni)
            runOnUiThread {
                btnLogin.isEnabled = true
                if (usuario != null) {
                    sessionManager.guardarSesion(usuario.dni, usuario.nombre, usuario.rol?.nombre ?: "OPERARIO")
                    irAlMenu()
                } else {
                    tvError.text = "DNI no encontrado o sin conexion al servidor"
                    tvError.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun irAlMenu() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }); finish()
    }
}
