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
import com.example.myapp.helpers.Database
import androidx.appcompat.app.AppCompatActivity





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
            if(res == "VACIOS"){
                //BORDER COLOR
                binding.editTextTextPersonName.error = "Campos vacios"
                binding.editTextTextPassword.error = "Campos vacios"
            }
            else if (res!="ERROR") {
                args.putString("userId", res)
                findNavController().navigate(R.id.action_do_login, args)
            } else {
                Snackbar.make(view, "Usuario y contraseña incorrectos, intente de nuevo.", Snackbar.LENGTH_LONG).show()
            }
        }

        initBD()
    }


    private fun initBD(){
        var db: Database? = Database.getInstance(requireActivity().applicationContext)
        db?.usuarioDao()!!.deleteAll()
        db?.registerUser("rockbeat", "1234")
    }

    private fun validateUserPass(): String {
        return viewModel.doLogin(requireActivity().applicationContext)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}