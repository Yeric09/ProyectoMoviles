package com.proyecto.parkfinder.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;
import com.proyecto.parkfinder.objetos.Atraccion
import com.proyecto.parkfinder.objetos.Comentario
import com.proyecto.parkfinder.objetos.Usuario;

class DAO(context: Context?) : SQLiteOpenHelper(
    context,
    "parkfinder.db",
    null,
    1
) {
    val TABLA_USUARIO : String = "TABLA_USUARIO"
    val TABLA_ATRACCION: String = "TABLA_ATRACCION"
    val TABLA_COMENTARIO: String = "TABLA_COMENTARIO"

    val COL_ID : String = "ID"
    val COL_NOMBRE : String = "NOMBRE"
    val COL_CORREO : String = "CORREO"
    val COL_CONTRASENA : String = "CONTRASENA"
    val COL_ICONO : String = "ICONO"
    val COL_UBICACION : String = "UBICACION"
    val COL_CATEGORIA: String = "CATEGORIA"
    val COL_AMENIDADES: String = "AMENIDADES"
    val COL_COMENTARIO: String = "COMENTARIO"
    val COL_ID_USUARIO: String = "ID_USUARIO"
    val COL_ID_ATRACCION: String = "ID_ATRACCION"
    val COL_CANTIDAD_ESTRELLAS: String = "CANTIDAD_ESTRELLAS"


    //Crear base de datos
    override fun onCreate(db: SQLiteDatabase?) {

        //Crea la tabla Usuarios en la base de datos
        val crearTabla : String =
            "CREATE TABLE $TABLA_USUARIO ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NOMBRE TEXT, $COL_CORREO TEXT, $COL_CONTRASENA TEXT, $COL_ICONO BLOB)"

        val crearTablaAtraccion: String =
            "CREATE TABLE $TABLA_ATRACCION ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NOMBRE TEXT, $COL_UBICACION TEXT, $COL_CATEGORIA TEXT, $COL_AMENIDADES TEXT, $COL_ICONO BLOB)"

        val crearTablaComentario: String =
            "CREATE TABLE $TABLA_COMENTARIO($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_ID_USUARIO INTEGER, $COL_ID_ATRACCION INTEGER, $COL_COMENTARIO TEXT, $COL_CANTIDAD_ESTRELLAS INTEGER, $COL_ICONO BLOB, FOREIGN KEY($COL_ID_USUARIO) REFERENCES $TABLA_USUARIO($COL_ID), FOREIGN KEY($COL_ID_ATRACCION) REFERENCES $TABLA_ATRACCION($COL_ID))"


        db?.execSQL(crearTabla)
        db?.execSQL(crearTablaAtraccion)
        db?.execSQL(crearTablaComentario)
    }

    //Se puede dejar en blanco
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    // LOGICA USUARIO

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

    // LOGICA ATRACCION
    fun agregarAtraccion(atraccion: Atraccion): Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val cv : ContentValues = ContentValues()

        cv.put(COL_NOMBRE, atraccion.nombre)
        cv.put(COL_UBICACION, atraccion.ubicacion)
        cv.put(COL_CONTRASENA, atraccion.categoria)
        cv.put(COL_AMENIDADES, atraccion.amenidades.joinToString(separator = "/"))

        val insert = db.insert(TABLA_ATRACCION, null, cv)
        return insert != -1L
    }

    fun getAtracciones_X_Categoria(categoria: String): MutableList<Atraccion> {
        val db : SQLiteDatabase = this.writableDatabase
        val query : String =
            "SELECT * FROM $TABLA_ATRACCION WHERE $COL_CATEGORIA = '$categoria'";

        val cursor : Cursor = db.rawQuery(query, null)

        val atracciones: MutableList<Atraccion> = mutableListOf()

        try {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getInt(0))
                    val nombre = cursor.getString(1)
                    val ubicacion = cursor.getString(2)
                    val amenidades = cursor.getString(3)
                    val categoria = cursor.getString(4)
                    val icono : ByteArray = cursor.getBlob(5)
                    val atraccion = Atraccion(id, nombre, ubicacion, categoria, amenidades.split("/").toTypedArray(), icono)
                    atracciones.add(atraccion)
                } while (cursor.moveToNext())
            }
        } finally {
            cursor.close()
        }
        return atracciones
    }

    // LOGICA COMENTARIO

    fun agregarComentario(comentario: Comentario): Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val cv : ContentValues = ContentValues()

        cv.put(COL_ID, comentario.id)
        cv.put(COL_ID_USUARIO, comentario.usuario.id)
        cv.put(COL_ID_ATRACCION, comentario.atraccion.id)
        cv.put(COL_COMENTARIO, comentario.comentario)
        cv.put(COL_CANTIDAD_ESTRELLAS, comentario.cantidadEstrellas)
        cv.put(COL_ICONO, comentario.foto)


        val insert = db.insert(TABLA_COMENTARIO, null, cv)
        return insert != -1L
    }

}