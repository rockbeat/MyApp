package com.example.myapp.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.example.myapp.model.Beer
import com.squareup.picasso.Picasso

class BeerDetailViewModel : ViewModel() {
    lateinit var beer: Beer

    fun getVolume(): String {
        return beer.volume?.value.toString() + " " + beer.volume?.unit.toString()
    }

    fun getFoodPairing(): String {
        var foods: String = ""

        for (food in beer.food_pairing!!) {
            foods += food + "\n"
        }
        return foods
    }

    fun getHops(): String {
        var hops: String = ""

        for (hop in beer.ingredients!!.hops!!) {
            hops += hop.name.toString() + " " + hop.amount!!.value.toString() + " " + hop.amount!!.unit.toString() + "\n"
        }
        return hops
    }

    fun getMalt(): String {
        var malts = ""

        for (malt in beer.ingredients!!.malt!!) {
            malts += malt.name.toString() + " " + malt.amount!!.value.toString() + " " + malt.amount!!.unit.toString() + "\n"
        }
        return malts
    }

    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }
}

@BindingAdapter("app:imageUri")
fun loadImageWithUri(imageView: ImageView, imageUri: String){
    Picasso.with(imageView.context).load(imageUri).into(imageView)
}