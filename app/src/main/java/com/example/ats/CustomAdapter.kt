package com.example.ats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter( /*private val itemClickListener: AdapterView.OnItemClickListener*/
                    ) : RecyclerView.Adapter< CustomAdapter.ViewHolder>(){

    val cursos = arrayOf(
                        "ARITMETICA",
                        "BIOLOGIA",
                        "GEOGRAFIA",
                        "LENGUAJE",
                        "LITERATURA",
                        "TRIGONOMETRIA"
                        )

    val imagenes = intArrayOf(
            R.drawable.icon_google,
            R.drawable.ico_paypal,
            R.drawable.icono_practi )

    interface OnPracticaClickListener{
        fun onItemClick()
    }

    inner class ViewHolder( itemView:View ) :RecyclerView.ViewHolder( itemView ){

        var itemCurso : TextView =itemView.findViewById( R.id.txtCurso )

        init{
            itemCurso.setOnClickListener(){
                v: View ->
                val position: Int = adapterPosition
                val positionActualizado = absoluteAdapterPosition
                var bundle = Bundle().apply {
                    val post = positionActualizado.toString()
                    putString("key_curso",post)
                }

                val intent = Intent( itemView.context, frame_11::class.java ).apply {
                    putExtras( bundle )
                }
                itemView.context.startActivity( intent )
                //Toast.makeText(itemView.context, "Precionaste # ${position +1} mientras que ${positionActualizado +1}",Toast.LENGTH_SHORT).show()
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