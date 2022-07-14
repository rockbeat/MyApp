package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.FragmentBeersBinding
import com.example.myapp.helpers.BeersRecyclerAdapter
import com.example.myapp.model.Beer
import com.example.myapp.viewmodel.BeersViewModel
import kotlinx.coroutines.*

class BeersFragment : Fragment() {

    private lateinit var binding: FragmentBeersBinding
    private val viewModel: BeersViewModel by viewModels()
    lateinit var mRecyclerView: RecyclerView
    val mAdapterBeers: BeersRecyclerAdapter = BeersRecyclerAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beers, container, false);
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setUpRecyclerView()
        return binding.root
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
                LinearLayoutManager(requireActivity().applicationContext)
            mRecyclerView.adapter = mAdapterBeers
            mAdapterBeers.recyclerAdapter(res, requireActivity().applicationContext)
        }

    }

}