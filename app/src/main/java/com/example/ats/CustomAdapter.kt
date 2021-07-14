package com.example.ats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter : RecyclerView.Adapter< CustomAdapter.ViewHolder>(){

    val cursos = arrayOf(
                        "GEOMETRIA",
                        "ARITMETICA",
                        "TRIGONOMETRIA",
                        "ALGEBRA",
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
                        "NOSE MANITO"
                        )

    val imagenes = intArrayOf(
            R.drawable.icon_google,
            R.drawable.ico_paypal,
            R.drawable.icono_practi )

    inner class ViewHolder( itemView:View ) :RecyclerView.ViewHolder( itemView ){

        var itemCurso : TextView =itemView.findViewById( R.id.txtCurso )

        init{
            itemCurso.setOnClickListener(){
                v: View ->
                val position: Int = adapterPosition
                val nombre = absoluteAdapterPosition
                Toast.makeText(itemView.context, "Precionaste # ${position +1} mientras que ${nombre +1}",Toast.LENGTH_SHORT).show()
            }
        }
    }
    

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from( viewGroup.context ).inflate( R.layout.card_layout, viewGroup, false )
        return ViewHolder( v )
    }

    override fun onBindViewHolder(viewHolder: CustomAdapter.ViewHolder, i: Int) {
        viewHolder.itemCurso.text = cursos[i]
    }

    override fun getItemCount(): Int {
        return cursos.size
    }

}