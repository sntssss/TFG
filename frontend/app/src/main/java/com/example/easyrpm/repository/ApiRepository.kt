package com.example.easyrpm.repository
import com.example.easyrpm.model.*
import com.example.easyrpm.network.RetrofitClient

object ApiRepository {
    private val api = RetrofitClient.apiService

    suspend fun login(dni: String): Usuario? = try {
        api.obtenerUsuarios().body()?.firstOrNull { it.dni == dni }
    } catch (e: Exception) { null }

    suspend fun getHus(): List<HU> = try {
        api.obtenerHus().body() ?: emptyList()
    } catch (e: Exception) { emptyList() }

    suspend fun getHusPendientes(): List<HU> {
        val todasHus = getHus()
        val ubicaciones = getUbicaciones()
        val ssccsConUbicacion = ubicaciones.flatMap { it.listaHu ?: emptyList() }.map { it.sscc }.toSet()
        return todasHus.filter { it.sscc !in ssccsConUbicacion }
    }

    suspend fun addHu(hu: HU): String? = try {
        val r = api.addHu(hu)
        if (r.isSuccessful) null else r.errorBody()?.string() ?: "Error"
    } catch (e: Exception) { null }

    suspend fun eliminarHu(sscc: Long): Boolean = try {
        api.eliminarHu(sscc).isSuccessful
    } catch (e: Exception) { false }

    suspend fun asignarUbicacion(hu: HU, ubicacion: Ubicacion): Boolean = try {
        api.editarHu(hu.sscc, hu.copy(ubicacion = ubicacion)).isSuccessful
    } catch (e: Exception) { false }

    suspend fun getMateriales(): List<Material> = try {
        api.obtenerMateriales().body() ?: emptyList()
    } catch (e: Exception) { emptyList() }

    suspend fun getUbicaciones(): List<Ubicacion> = try {
        api.obtenerUbicaciones().body() ?: emptyList()
    } catch (e: Exception) { emptyList() }

    suspend fun getProveedores(): List<Proveedor> = try {
        api.obtenerProveedores().body() ?: emptyList()
    } catch (e: Exception) { emptyList() }
}
