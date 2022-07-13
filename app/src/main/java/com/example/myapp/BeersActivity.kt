package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.model.Beer
import kotlinx.coroutines.*
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.ActivityBeersBinding
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.helpers.RecyclerAdapter
import com.example.myapp.viewmodel.BeersViewModel
import com.example.myapp.viewmodel.MainViewModel

class BeersActivity : AppCompatActivity() {

    private lateinit var mItems: ArrayList<String>
    private lateinit var binding: ActivityBeersBinding
    private val viewModel: BeersViewModel by viewModels()
    lateinit var mRecyclerView: RecyclerView
    val mAdapter: RecyclerAdapter = RecyclerAdapter()
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var idPersona: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_beers);
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        val deferred1: Deferred<MutableList<Beer>> = coroutineScope.async {
            viewModel.getBeers()
        }


        coroutineScope.launch(Dispatchers.Main) {
            var res = deferred1.await()
            mRecyclerView = binding.beersRecycler
            mRecyclerView.setHasFixedSize(true)
            mRecyclerView.layoutManager =
                LinearLayoutManager(applicationContext)
            mRecyclerView.adapter = mAdapter
            mAdapter.recyclerAdapter(res, applicationContext)
        }

    }
}