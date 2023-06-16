package com.proyecto.parkfinder.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;
import com.proyecto.parkfinder.objetos.Usuario;

class DAO(context: Context?) : SQLiteOpenHelper(
    context,
    "parkfinder.db",
    null,
    1
) {
    val TABLA_USUARIO : String = "TABLA_USUARIO"

    val COL_ID : String = "ID"
    val COL_NOMBRE : String = "NOMBRE"
    val COL_CORREO : String = "CORREO"
    val COL_CONTRASENA : String = "CONTRASENA"
    val COL_ICONO : String = "ICONO"

    //Crear base de datos
    override fun onCreate(db: SQLiteDatabase?) {

        val crearTabla : String =
            "CREATE TABLE $TABLA_USUARIO ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NOMBRE TEXT, $COL_CORREO TEXT, $COL_CONTRASENA TEXT, $COL_ICONO BLOB)"

        db?.execSQL(crearTabla)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun agregarUsuario(usuario: Usuario): Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val cv : ContentValues = ContentValues()

        cv.put(COL_NOMBRE, usuario.nombre)
        cv.put(COL_CORREO, usuario.correo)
        cv.put(COL_CONTRASENA, usuario.contrasena)
        cv.put(COL_ICONO, usuario.icono)

        val insert = db.insert(TABLA_USUARIO, null, cv)
        return insert != -1L
    }

}