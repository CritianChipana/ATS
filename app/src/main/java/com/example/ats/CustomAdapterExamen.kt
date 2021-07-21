package com.example.ats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterExamen : RecyclerView.Adapter<CustomAdapterExamen.ViewHolder>(){

    val universidad = arrayOf(
        "UNMSM",
        "UNTELS",
        "UNI",
        "UNALM",
        "UNFV",

    )

    val imagenes_u = intArrayOf(
        R.drawable.sanmarcos,
        R.drawable.untels,
        R.drawable.uni,
        R.drawable.agraria,
        R.drawable.villareal )


    inner class ViewHolder( itemView: View ):RecyclerView.ViewHolder( itemView ){

        var itemImgen :ImageView = itemView.findViewById( R.id.imgUniversidad )
        var itemTitulo : TextView = itemView.findViewById( R.id.nombreUniversidad )


        init {
            itemTitulo.setOnClickListener(){
                    v: View ->
                val position: Int = adapterPosition
                val positionActualizado = absoluteAdapterPosition
                var bundle = Bundle().apply {
                    val post = positionActualizado.toString()
                    putString("key_uni",post)
                }

                val intent = Intent( itemView.context, PreguntaSimulacro::class.java ).apply {
                    putExtras( bundle )
                }
                itemView.context.startActivity( intent )
                //Toast.makeText(itemView.context, "Precionaste # ${position +1} mientras que ${positionActualizado +1}",Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomAdapterExamen.ViewHolder {
        val v = LayoutInflater.from( viewGroup.context ).inflate( R.layout.card_layout_exam, viewGroup, false )
        return ViewHolder( v )
    }

    override fun onBindViewHolder(viewHolder: CustomAdapterExamen.ViewHolder, i: Int) {
        viewHolder.itemImgen.setImageResource(imagenes_u[i])
        viewHolder.itemTitulo.text = universidad[i]
    }

    override fun getItemCount(): Int {
        return universidad.size
    }


}