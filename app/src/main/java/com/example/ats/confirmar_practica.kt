package com.example.ats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ats.databinding.ActivityConfirmarpracticaBinding


class confirmar_practica : AppCompatActivity() {

    //private lateinit var binding : ActivityConfirmarpracticaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
      val  binding = ActivityConfirmarpracticaBinding.inflate( layoutInflater )
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        val bundbleReception = intent.extras

        if( bundbleReception != null ){
            var id = bundbleReception!!.getString("key_curso")
            println("*************************************************************")
            println(id)

            println("*************************************************************")
            println("*************************************************************")
            binding.txtId.setText( id )
        }


    }


}