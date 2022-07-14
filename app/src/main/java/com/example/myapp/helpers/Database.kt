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
import com.example.myapp.model.Beer
import com.google.gson.Gson

@Database(entities = [UsuarioEntity::class, FavouriteEntity::class], version = 2)
abstract class Database : RoomDatabase() {

    abstract fun usuarioDao(): UsuariosDAO
    abstract fun favouriteDao(): FavouriteDAO

    fun registerUser(usuario: String, password: String) {
        var passEncoded: String = Base64.encodeToString(password.toByteArray(), 0)
        usuarioDao().registerUser(UsuarioEntity(1, usuario, passEncoded))
    }

    fun doLogin(usuario: String, password: String): MutableList<UsuarioEntity> {
        var passEncoded: String = Base64.encodeToString(password.toByteArray(), 0)
        return usuarioDao().validateUser(usuario, passEncoded)
    }

    fun saveFavourite(beer : Beer) {
        favouriteDao().saveFavourite(FavouriteEntity(beer.id, Gson().toJson(beer)))
    }

    fun deleteFavourite(beer : Beer) {
        favouriteDao().deleteFavourite(FavouriteEntity(beer.id, Gson().toJson(beer)))
    }

    fun isBeerFavourite(idBeer: Int): Boolean {
        var result = favouriteDao().getBeerFavourite(idBeer)
        return result != null
    }

    fun getAllFavourites() : List<FavouriteEntity>{
        var result = favouriteDao().getAllFavourites()
        return result
    }

    fun rateBeer(idBeer: Int, beer : String, rate : Float){
        var beer = FavouriteEntity(idBeer,beer,rate)
        favouriteDao().rateBeer(beer)
    }

    companion object {
        private var INSTANCE: com.example.myapp.helpers.Database? = null

        fun getInstance(context: Context): com.example.myapp.helpers.Database? {
            if (INSTANCE == null) {
                synchronized(com.example.myapp.helpers.Database::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        com.example.myapp.helpers.Database::class.java, "users.db"
                    ).allowMainThreadQueries()
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

