package com.proyecto.parkfinder.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

        //Crea la tabla Usuarios en la base de datos
        val crearTabla : String =
            "CREATE TABLE $TABLA_USUARIO ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NOMBRE TEXT, $COL_CORREO TEXT, $COL_CONTRASENA TEXT, $COL_ICONO BLOB)"

        db?.execSQL(crearTabla)
    }

    //Se puede dejar en blanco
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    //Agrega un usuario nuevo a la base de datos. Usado en RegistroUsuario
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

    //Retorna un usuario dado correo y contrasena. Usado para login
    fun getUsuarioParaLogin(correo: String, contrasena: String): Usuario? {
        val db : SQLiteDatabase = this.writableDatabase
        val query : String =
            "SELECT * FROM $TABLA_USUARIO WHERE $COL_CORREO = '$correo' AND $COL_CONTRASENA = '$contrasena'";

//        val resultado : Array<String> = emptyArray()
        val cursor : Cursor = db.rawQuery(query, null)

        //Crear objeto usuario para retornar
        if(cursor.moveToFirst()) {
            val id : Int = cursor.getInt(0)
            val nombre : String = cursor.getString(1)
            val correo : String = cursor.getString(2)
            val contrasena : String = cursor.getString(3)
            val icono : ByteArray = cursor.getBlob(4)

            return Usuario(id, nombre, correo, contrasena, icono)
        } else {
            return null
        }
    }

}