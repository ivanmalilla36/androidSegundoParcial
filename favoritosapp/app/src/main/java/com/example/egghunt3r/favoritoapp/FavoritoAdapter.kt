package com.example.egghunt3r.favoritoapp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorito.view.*

class FavoritoAdapter(private val favoritoList: FavoritoResult, private val context : Context) : RecyclerView.Adapter<FavoritoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // en este metoo se va a sobres-ecribir la lista por la lista costum llamado favoritoAdapter.xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorito, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //llena la posicion de acuerdo a lo que responde el web service
        holder.bindRepo(favoritoList.favoritos[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context,DetailFavorito::class.java)

            intent.putExtra("title",favoritoList.favoritos[position].title)
            intent.putExtra("description",favoritoList.favoritos[position].description)
            intent.putExtra("url",favoritoList.favoritos[position].url)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = favoritoList.favoritos.size
    //Numero de elementos que va a mostrar en la lista

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Esta es la clase que llena la informacion
        fun bindRepo(favorito: Item) {
            with(favorito) {
                itemView.title.text = favorito.title
                itemView.description.text = favorito.description
                //itemView.url.text = favorito.url
                Picasso.get().load(favorito.url).into(itemView.icon);
            }
        }
    }
}