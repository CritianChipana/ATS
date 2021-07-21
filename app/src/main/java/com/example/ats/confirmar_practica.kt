package com.example.ats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ats.databinding.ActivityConfirmarpracticaBinding


class confirmar_practica : AppCompatActivity() {

    //private lateinit var binding : ActivityConfirmarpracticaBinding

    private lateinit var materia:String

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

        binding.cpatrasBtn.setOnClickListener(){
            var intent = Intent( this, Prueba_Simulacion::class.java )
            startActivity( intent )
        }

     /*   binding.cplistoBtn.setOnClickListener(){
           // var idq = bundbleReception!!.getString("key_curso")
            val bundle2 = Bundle()
            var nombre = "CRISTIAN"
            //bundle.putString( "key_id2",idq )
            bundle2.putString( "key_nombre",nombre )
            var intent2 = Intent( this, frame_11::class.java )
            startActivity( intent2 )
        }*/


    }


}