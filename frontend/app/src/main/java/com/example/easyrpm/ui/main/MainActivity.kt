package com.example.easyrpm.ui.main
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.easyrpm.R
import com.example.easyrpm.ui.ajustes.AjustesActivity
import com.example.easyrpm.ui.buscar.EncontrarHuActivity
import com.example.easyrpm.ui.pendientes.HusPendientesActivity
import com.example.easyrpm.ui.recepcion.NuevaRecepcionActivity
import com.example.easyrpm.utils.SessionManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sm = SessionManager(this)
        findViewById<TextView>(R.id.tvBienvenida).text = "Bienvenido, ${sm.getNombre()}"
        findViewById<CardView>(R.id.cardNuevaRecepcion).setOnClickListener { startActivity(Intent(this, NuevaRecepcionActivity::class.java)) }
        findViewById<CardView>(R.id.cardHusPendientes).setOnClickListener { startActivity(Intent(this, HusPendientesActivity::class.java)) }
        findViewById<CardView>(R.id.cardEncontrarHU).setOnClickListener { startActivity(Intent(this, EncontrarHuActivity::class.java)) }
        findViewById<Button>(R.id.btnAjustes).setOnClickListener { startActivity(Intent(this, AjustesActivity::class.java)) }
    }
}