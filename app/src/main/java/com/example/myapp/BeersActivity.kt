package com.example.myapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.ActivityBeersBinding
import com.example.myapp.helpers.RecyclerAdapter
import com.example.myapp.model.Beer
import com.example.myapp.viewmodel.BeersViewModel
import kotlinx.coroutines.*
import kotlin.system.exitProcess

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                showExitDialog()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        showExitDialog()
    }

    private fun showExitDialog(){

        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.dialog_title)
            .setMessage(R.string.dialog_exit_message)
            .setNegativeButton(R.string.dialog_cancel_btn) { view, _ ->
                view.dismiss()
            }
            .setPositiveButton(R.string.dialog_accept_btn) { view, _ ->
                view.dismiss()
                moveTaskToBack(true);
                exitProcess(-1)
            }
            .setCancelable(false)
            .create()

        dialog.show()
    }
    companion object {

        const val TAG = "BeerDetailDialog"


        fun newInstance(): BeersActivity {
            val activity = BeersActivity()
            return activity
        }

    }
}