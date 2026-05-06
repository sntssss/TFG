package com.example.easyrpm.model

data class HU(
    val sscc: Long = 0,
    val lote: Long = 0,
    val peso: Double = 0.0,
    val fecha_caducidad: String? = null,
    val ubicacion: Ubicacion? = null,
    val proveedor: Proveedor? = null,
    val material: Material? = null
)

data class HUSimple(val sscc: Long = 0)

data class Usuario(
    val dni: String = "",
    val nombre: String = "",
    val apellidos: String = "",
    val rol: Rol? = null
)

data class Rol(val id: Long = 0, val nombre: String = "")
data class Material(val id: Long = 0, val nombre: String = "")
data class Ubicacion(val id: Long = 0, val nombre: String = "", val listaHu: List<HUSimple>? = null)
data class Proveedor(val id: Long = 0, val nombre: String = "")
