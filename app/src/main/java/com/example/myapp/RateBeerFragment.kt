package com.example.myapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.RateBeerFragmentBinding
import com.example.myapp.db.FavouriteEntity
import com.example.myapp.helpers.FavouritesRecyclerAdapter
import com.example.myapp.viewmodel.RateBeerViewModel
import kotlinx.coroutines.*

class RateBeerFragment : Fragment() {

    companion object {
        fun newInstance() = RateBeerFragment()
    }

    private lateinit var binding: RateBeerFragmentBinding
    private lateinit var viewModel: RateBeerViewModel
    lateinit var mRecyclerView: RecyclerView
    val mAdapter: FavouritesRecyclerAdapter = FavouritesRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.rate_beer_fragment, container, false);
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RateBeerViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun setUpRecyclerView() {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        val deferred1: Deferred<MutableList<FavouriteEntity>> = coroutineScope.async {
            viewModel.getBeers(requireActivity().applicationContext)
        }

        coroutineScope.launch(Dispatchers.Main) {
            var res = deferred1.await()
            mRecyclerView = binding.beerRateRecycler
            mRecyclerView.setHasFixedSize(true)
            mRecyclerView.layoutManager =
                LinearLayoutManager(requireActivity().applicationContext)
            mRecyclerView.adapter = mAdapter
            mAdapter.recyclerAdapter(res, requireActivity().applicationContext)
        }

    }

}