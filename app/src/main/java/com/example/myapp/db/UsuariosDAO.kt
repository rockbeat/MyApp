package com.example.myapp.db

import androidx.room.*

@Dao
interface UsuariosDAO {
    @Query("SELECT * FROM users WHERE name = :name AND password = :password")
    fun validateUser(name: String, password : String): MutableList<UsuarioEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun registerUser(user : UsuarioEntity)

    @Delete
    fun deleteUser(user : UsuarioEntity)

    @Query("DELETE FROM users")
    fun deleteAll()
}