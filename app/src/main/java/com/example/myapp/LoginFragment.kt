package com.example.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapp.databinding.FragmentLoginBinding
import com.example.myapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.example.myapp.db.UsuarioEntity
import com.example.myapp.db.UsuariosDAO
import com.example.myapp.helpers.Database


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener {
            val args = Bundle()
            var res = validateUserPass()
            if (res!="ERROR") {
                args.putString("userId", res)
                findNavController().navigate(R.id.action_do_login, args)
            } else {

                Snackbar.make(view, "Usuario incorrecto", Snackbar.LENGTH_LONG)
                    .setAction("Recuperar contraseña", View.OnClickListener {
                        findNavController().navigate(R.id.action_recover_password)
                    }).show()
            }
        }

        initBD()
    }


    private fun initBD(){
        var db: UsuariosDAO = Database.getInstance(requireActivity().applicationContext)?.usuarioDao()!!
        //db.deleteAll()
        db.registerUser(UsuarioEntity(1, "rockbeat", "1234"))
    }

    private fun validateUserPass(): String {
        return viewModel.doLogin(requireActivity().applicationContext)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}