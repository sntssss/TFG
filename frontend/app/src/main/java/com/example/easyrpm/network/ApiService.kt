package com.example.easyrpm.network
import com.example.easyrpm.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Login con Dni y password - endpoint nuevo del backend
    @POST("login/")
    suspend fun login(@Body usuario: Usuario): Response<Usuario>

    @GET("usuarios") suspend fun obtenerUsuarios(): Response<List<Usuario>>
    @POST("usuarios/add") suspend fun anadirUsuario(@Body u: Usuario): Response<String>
    @PUT("usuarios/edit/{dni}") suspend fun editarUsuario(@Path("dni") dni: String, @Body u: Usuario): Response<String>

    @GET("hus") suspend fun obtenerHus(): Response<List<HU>>
    @POST("hus/add") suspend fun addHu(@Body hu: HU): Response<String>
    @PUT("hus/edit/{sscc}") suspend fun editarHu(@Path("sscc") sscc: Long, @Body hu: HU): Response<String>
    @POST("hus/delete") suspend fun eliminarHu(@Body sscc: Long): Response<String>

    @GET("materiales") suspend fun obtenerMateriales(): Response<List<Material>>
    @GET("ubicaciones") suspend fun obtenerUbicaciones(): Response<List<Ubicacion>>
    @GET("proveedores") suspend fun obtenerProveedores(): Response<List<Proveedor>>
}
