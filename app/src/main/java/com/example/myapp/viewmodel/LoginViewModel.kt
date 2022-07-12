package com.example.myapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.db.UsuarioEntity
import com.example.myapp.db.UsuariosDAO
import com.example.myapp.helpers.Database
import com.example.myapp.model.Usuario

class LoginViewModel : ViewModel() {
    val usuario = MutableLiveData<Usuario>(Usuario("", ""))

    fun doLogin(context : Context): String {
        if(usuario.value?.name.isNullOrEmpty() || usuario.value?.password.isNullOrEmpty()){
            return "VACIOS"
        }

        var db: Database? = Database.getInstance(context)
        var result = db!!.doLogin(usuario.value?.name.toString(), usuario.value?.password.toString())

        return if(result.count() > 0){
            result[0].id.toString()
        }else{
            "ERROR"
        }
    }
}