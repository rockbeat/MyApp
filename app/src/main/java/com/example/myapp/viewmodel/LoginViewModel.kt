package com.example.myapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.db.UsuariosDAO
import com.example.myapp.helpers.Database
import com.example.myapp.model.Usuario

class LoginViewModel : ViewModel() {
    val usuario = MutableLiveData<Usuario>(Usuario("", ""))

    fun doLogin(context : Context): String {
        var user = usuario.value?.name
        var pwd = usuario.value?.password
        var db: UsuariosDAO = Database.getInstance(context)?.usuarioDao()!!
        var result = db.validateUser(user.toString(), pwd.toString())

        return if(result.count() > 0){
            result[0].id.toString()
        }else{
            "ERROR"
        }
    }
}