package com.proyecto.parkfinder.objetos

class Atraccion constructor(
    var id: Int = -1,
    var nombre: String = "",
    var ubicacion: String = "",
    var categoria: String = "",
    var amenidades: Array<String> = emptyArray(),
    var foto: ByteArray? = null //Es la imagen
    )