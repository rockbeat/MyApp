package com.example.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.myapp.databinding.BeerDetailDialogBinding
import com.example.myapp.model.Beer
import com.example.myapp.viewmodel.BeerDetailViewModel
import com.google.gson.Gson

class BeerDetailDialog : DialogFragment() {
    lateinit var beer: Beer
    private lateinit var binding: BeerDetailDialogBinding
    private val viewModel: BeerDetailViewModel by viewModels()

    companion object {

        const val TAG = "BeerDetailDialog"

        fun newInstance(beer: Beer): BeerDetailDialog {
            val args = Bundle()

            args.putString("beer", Gson().toJson(beer))
            val fragment = BeerDetailDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mArgs = arguments
        val beer = mArgs!!.getString("beer")
        this.beer = Gson().fromJson(beer, Beer::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.beer_detail_dialog, container, false);
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.beer = this.beer
        return binding.root
        //return inflater.inflate(R.layout.beer_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView(view: View) {


    }

    private fun setupClickListeners(view: View) {


    }

}