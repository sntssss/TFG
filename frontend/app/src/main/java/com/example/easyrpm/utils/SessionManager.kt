package com.example.easyrpm.utils
import android.content.Context
import android.content.SharedPreferences
class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("easyrpm_session", Context.MODE_PRIVATE)
    fun guardarSesion(dni: String, nombre: String, rol: String) { prefs.edit().putString("dni", dni).putString("nombre", nombre).putString("rol", rol).putBoolean("logado", true).apply() }
    fun estaLogado(): Boolean = prefs.getBoolean("logado", false)
    fun getNombre(): String = prefs.getString("nombre", "") ?: ""
    fun getRol(): String = prefs.getString("rol", "") ?: ""
    fun getDni(): String = prefs.getString("dni", "") ?: ""
    fun cerrarSesion() { prefs.edit().clear().apply() }
}