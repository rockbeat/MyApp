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


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var beers: MutableList<Beer> = ArrayList()
    lateinit var context: Context

    fun recyclerAdapter(beers: MutableList<Beer>, context: Context) {
        this.beers = beers
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = beers.get(position)
        holder.bind(item, context)

        var db: Database? = Database.getInstance(context)
        var isFav = db!!.isBeerFavourite(item.id)
        holder.favCheck.isChecked = isFav
        holder.favCheck.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                //set your object's last status
                if (isChecked) db.saveFavourite(item.id)
                else db.deleteFavourite(item.id)
            }
        })
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
        fun bind(beer: Beer, context: Context) {
            beerName.text = beer.name
            beerTagline.text = beer.tagline
            itemView.setOnClickListener(View.OnClickListener {
                val dialogFragment: BeerDetailDialog = BeerDetailDialog.newInstance(beer)
                val activity = it.context as AppCompatActivity
                dialogFragment.show(activity.supportFragmentManager, null)

                Toast.makeText(
                    context,
                    beer.method?.twist.toString(),
                    Toast.LENGTH_LONG
                ).show()
            })
            beerImg.loadUrl(beer.image_url!!)
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}

