package com.example.ats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterExamen : RecyclerView.Adapter<CustomAdapterExamen.ViewHolder>(){

    val universidad = arrayOf(
        "GEOMETRIA",
        "ARITMETICA",
        "TRIGONOMETRIA",
        /*       "ALGEBRA",
             "BIOLOGIA",
              "QUMICA",
              "FISICA",
              "LENGUAJE",
              "LITERATURA",
              "FILOSOFIA",
              "LOGICA",
              "ECONOMIA",
              "CIVICA",
              "PSICOLOGIA",
              "GEOGRAFIA",
              "NOSE MANITO"*/
    )

    val imagenes_u = intArrayOf(
        R.drawable.icon_google,
        R.drawable.ico_paypal,
        R.drawable.icono_practi )


    inner class ViewHolder( itemView: View ):RecyclerView.ViewHolder( itemView ){

        var itemImgen :ImageView = itemView.findViewById( R.id.imgUniversidad )
        var itemTitulo : TextView = itemView.findViewById( R.id.nombreUniversidad )


        init {

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