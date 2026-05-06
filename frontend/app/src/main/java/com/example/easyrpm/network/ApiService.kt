package com.example.easyrpm.network
import com.example.easyrpm.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("usuarios") suspend fun obtenerUsuarios(): Response<List<Usuario>>
    @GET("usuarios/{dni}") suspend fun usuarioPorDni(@Path("dni") dni: String): Response<Usuario>
    @POST("usuarios/add") suspend fun anadirUsuario(@Body u: Usuario): Response<String>
    @POST("usuarios/delete/{dni}") suspend fun eliminarUsuario(@Path("dni") dni: String): Response<String>
    @PUT("usuarios/edit/{dni}") suspend fun editarUsuario(@Path("dni") dni: String, @Body u: Usuario): Response<String>

    @GET("hus") suspend fun obtenerHus(): Response<List<HU>>
    @GET("hus/{sscc}") suspend fun huPorSscc(@Path("sscc") sscc: Long): Response<HU>
    @POST("hus/add") suspend fun addHu(@Body hu: HU): Response<String>
    @POST("hus/delete") suspend fun eliminarHu(@Body sscc: Long): Response<String>
    @PUT("hus/edit/{sscc}") suspend fun editarHu(@Path("sscc") sscc: Long, @Body hu: HU): Response<String>

    @GET("materiales") suspend fun obtenerMateriales(): Response<List<Material>>
    @POST("materiales/add") suspend fun nuevoMaterial(@Body m: Material): Response<String>

    @GET("ubicaciones") suspend fun obtenerUbicaciones(): Response<List<Ubicacion>>
    @POST("ubicaciones/add") suspend fun nuevaUbicacion(@Body u: Ubicacion): Response<String>

    @GET("proveedores") suspend fun obtenerProveedores(): Response<List<Proveedor>>
    @POST("proveedores/add") suspend fun nuevoProveedor(@Body p: Proveedor): Response<String>
}
