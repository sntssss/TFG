package com.example.easyrpm.network
import com.example.easyrpm.model.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("usuarios")
    suspend fun obtenerUsuarios(): Response<List<Usuario>>

    @GET("usuarios/{dni}")
    suspend fun usuarioPorDni(@Path("dni") dni: String): Response<ResponseBody>

    @POST("usuarios/add")
    suspend fun anadirUsuario(@Body u: Usuario): Response<ResponseBody>

    @GET("hus")
    suspend fun obtenerHus(): Response<List<HU>>

    @GET("hus/{sscc}")
    suspend fun huPorSscc(@Path("sscc") sscc: Long): Response<ResponseBody>

    @POST("hus/add")
    suspend fun addHu(@Body hu: HU): Response<ResponseBody>

    @POST("hus/delete")
    suspend fun eliminarHu(@Body sscc: Long): Response<ResponseBody>

    @PUT("hus/edit/{sscc}")
    suspend fun editarHu(@Path("sscc") sscc: Long, @Body hu: HU): Response<ResponseBody>

    @GET("materiales")
    suspend fun obtenerMateriales(): Response<List<Material>>

    @POST("ubicaciones/add")
    suspend fun nuevaUbicacion(@Body u: Ubicacion): Response<ResponseBody>

    @GET("ubicaciones")
    suspend fun obtenerUbicaciones(): Response<List<Ubicacion>>

    @GET("proveedores")
    suspend fun obtenerProveedores(): Response<List<Proveedor>>
}
