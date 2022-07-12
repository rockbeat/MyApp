package com.example.myapp.helpers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.db.UsuarioEntity
import com.example.myapp.db.UsuariosDAO

@Database(entities = [UsuarioEntity::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun usuarioDao(): UsuariosDAO

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

