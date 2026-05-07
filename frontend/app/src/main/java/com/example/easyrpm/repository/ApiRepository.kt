package com.example.easyrpm.repository
import com.example.easyrpm.model.*
import com.example.easyrpm.network.RetrofitClient

object ApiRepository {
    private val api = RetrofitClient.apiService

    // Login usando el nuevo endpoint POST /login/ del backend
    suspend fun login(dni: String, password: String): Usuario? = try {
        val response = api.login(Usuario(dni = dni, password = password))
        if (response.isSuccessful) response.body() else null
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
        // Construimos una HU limpia solo con los campos necesarios
        // para evitar el hibernateLazyInitializer que rompe el parseo
        val huLimpia = HU(
            sscc = hu.sscc,
            lote = hu.lote,
            peso = hu.peso,
            fecha_caducidad = hu.fecha_caducidad,
            ubicacion = Ubicacion(id = ubicacion.id, nombre = ubicacion.nombre),
            material = hu.material?.let { Material(id = it.id, nombre = it.nombre) },
            proveedor = hu.proveedor?.let { Proveedor(id = it.id, nombre = it.nombre) }
        )
        api.editarHu(hu.sscc, huLimpia)
        true
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
