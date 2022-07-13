package com.example.myapp.helpers

import android.content.Context
import android.util.Base64
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.db.FavouriteDAO
import com.example.myapp.db.FavouriteEntity
import com.example.myapp.db.UsuarioEntity
import com.example.myapp.db.UsuariosDAO

@Database(entities = [UsuarioEntity::class, FavouriteEntity::class], version = 2)
abstract class Database : RoomDatabase() {

    abstract fun usuarioDao(): UsuariosDAO
    abstract fun favouriteDao(): FavouriteDAO

    fun registerUser(usuario : String, password : String){
        var passEncoded : String = Base64.encodeToString(password.toByteArray(), 0)
        usuarioDao().registerUser(UsuarioEntity(1, usuario, passEncoded))
    }

    fun doLogin(usuario : String, password : String) : MutableList<UsuarioEntity>{
        var passEncoded : String = Base64.encodeToString(password.toByteArray(), 0)
        return usuarioDao().validateUser(usuario, passEncoded)
    }

    fun saveFavourite(idBeer : Int){
        favouriteDao().saveFavourite(FavouriteEntity(idBeer,true))
    }

    fun deleteFavourite(idBeer : Int){
        favouriteDao().deleteFavourite(FavouriteEntity(idBeer,true))
    }

    fun isBeerFavourite(idBeer : Int) : Boolean{
        var result = favouriteDao().getBeerFavourite(idBeer)
        return result != null
    }

    companion object {
        private var INSTANCE: com.example.myapp.helpers.Database? = null

        fun getInstance(context: Context): com.example.myapp.helpers.Database? {
            if (INSTANCE == null) {
                synchronized(com.example.myapp.helpers.Database::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        com.example.myapp.helpers.Database::class.java, "users.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

