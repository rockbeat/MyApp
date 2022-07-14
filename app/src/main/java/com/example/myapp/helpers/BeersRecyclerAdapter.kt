package com.example.myapp.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.model.Beer
import com.squareup.picasso.Picasso
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.BeerDetailDialog
import android.util.SparseBooleanArray





class BeersRecyclerAdapter : RecyclerView.Adapter<BeersRecyclerAdapter.ViewHolder>() {
    var beers: MutableList<Beer> = ArrayList()
    lateinit var context: Context
    var itemStateArray = SparseBooleanArray()

    fun recyclerAdapter(beers: MutableList<Beer>, context: Context) {
        this.beers = beers
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = beers[position]
        holder.bind(item, context, position)
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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val beerName = view.findViewById(R.id.beerName) as TextView
        val beerTagline = view.findViewById(R.id.beerTagline) as TextView
        val beerImg = view.findViewById(R.id.beerImg) as ImageView
        val favCheck = view.findViewById(R.id.favIcon) as CheckBox
        fun bind(beer: Beer, context: Context, position : Int) {
            beerName.text = beer.name
            beerTagline.text = beer.tagline

            var db: Database? = Database.getInstance(context)
            var isFav = db!!.isBeerFavourite(beer.id)
            favCheck.isChecked = isFav
            itemStateArray.put(position, isFav)

            itemView.setOnClickListener {
                val dialogFragment: BeerDetailDialog = BeerDetailDialog.newInstance(beer)
                val activity = it.context as AppCompatActivity
                dialogFragment.show(activity.supportFragmentManager, null)
            }

            beerImg.loadUrl(beer.image_url!!)

            favCheck.setOnCheckedChangeListener { view, isChecked -> //set your object's last status
                var checked = itemStateArray.get(adapterPosition, false)
                if(!checked){
                    itemStateArray.put(adapterPosition, true)
                    db.saveFavourite(beer)
                }else{
                    itemStateArray.put(adapterPosition, false)
                    db.deleteFavourite(beer)
                }
            }
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}

