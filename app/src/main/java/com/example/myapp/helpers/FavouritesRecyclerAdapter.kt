package com.example.myapp.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.db.FavouriteEntity
import com.example.myapp.model.Beer
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class FavouritesRecyclerAdapter : RecyclerView.Adapter<FavouritesRecyclerAdapter.ViewHolder>() {
    var favourites: MutableList<FavouriteEntity> = ArrayList()
    lateinit var context: Context

    fun recyclerAdapter(favs: MutableList<FavouriteEntity>, context: Context) {
        this.favourites = favs
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favourites[position]
        holder.bind(item)
        holder.rate.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                // Called when the user swipes the RatingBar
                var db: Database? = Database.getInstance(context)
                db!!.rateBeer(item.idBeer, item.beer, rating)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.rate_beer_layout_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return favourites.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val beerName = view.findViewById(R.id.beerName) as TextView
        val beerTagline = view.findViewById(R.id.beerTagline) as TextView
        val beerImg = view.findViewById(R.id.beerImg) as ImageView
        val rate = view.findViewById(R.id.rate_beer) as RatingBar

        fun bind(fav: FavouriteEntity) {
            var beer = Gson().fromJson(fav.beer, Beer::class.java)
            beerName.text = beer.name
            beerTagline.text = beer.tagline
            beerImg.loadUrl(beer.image_url!!)
            rate.rating = fav.rate
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}

