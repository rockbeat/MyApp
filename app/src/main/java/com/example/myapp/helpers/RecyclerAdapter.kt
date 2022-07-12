package com.example.myapp.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.model.Beer
import com.squareup.picasso.Picasso

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var beers: MutableList<Beer> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(beers: MutableList<Beer>, context: Context) {
        this.beers = beers
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = beers.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.beer_layout_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val beerName = view.findViewById(R.id.beerName) as TextView
        val beerTagline = view.findViewById(R.id.beerTagline) as TextView
        val beerImg = view.findViewById(R.id.beerImg) as ImageView
        fun bind(beer: Beer, context: Context) {
            beerName.text = beer.name
            beerTagline.text = beer.tagline
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(
                    context,
                    beer.name,
                    Toast.LENGTH_SHORT
                ).show()
            })
            beerImg.loadUrl(beer.image_url)
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}

