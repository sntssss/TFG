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

    // Una HU está pendiente si aún no tiene ubicacion asignada
    suspend fun getHusPendientes(): List<HU> = getHus().filter { it.ubicacion == null }

    suspend fun addHu(hu: HU): String? = try {
        val response = api.addHu(hu)
        if (response.isSuccessful) null
        else response.errorBody()?.string() ?: "Error al registrar la HU"
    } catch (e: Exception) { e.message ?: "Error de red" }

    suspend fun eliminarHu(sscc: Long): Boolean = try {
        api.eliminarHu(sscc).isSuccessful
    } catch (e: Exception) { false }

    suspend fun asignarUbicacion(hu: HU, ubicacion: Ubicacion): Boolean = try {
        val ubSinLista = ubicacion.copy(listaHu = null)
        val huActualizada = hu.copy(ubicacion = ubSinLista)
        api.editarHu(hu.sscc, huActualizada).isSuccessful
    } catch (e: Exception) { false }

    suspend fun getMateriales(): List<Material> = try {
        api.obtenerMateriales().body() ?: emptyList()
    } catch (e: Exception) { emptyList() }

    suspend fun getUbicaciones(): List<Ubicacion> = try {
        api.obtenerUbicaciones().body() ?: emptyList()
    } catch (e: Exception) { emptyList() }
}
