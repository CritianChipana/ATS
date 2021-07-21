package com.example.ats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ats.databinding.ActivityFrame10Binding

class frame_10 : AppCompatActivity() {

    private lateinit var binding : ActivityFrame10Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFrame10Binding.inflate( layoutInflater )
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var bundlerespuestasJhonny =  intent.extras

        if(bundlerespuestasJhonny != null){

            var notita = bundlerespuestasJhonny.getString("key_nota")
            println("888888888 $notita")
            binding.txtNota.setText(notita)

        }else{
            println("11111111111111111111111111111111111111111111111111111112")
            println("11111111111111111111111111111111111111111111111111111113")
            println("11111111111111111111111111111111111111111111111111111114")
        }


        binding.btnSalir.setOnClickListener(){
            startActivity( Intent( this, MainActivity::class.java ) )
        }
        binding.btnVolverIntenta.setOnClickListener(){
            startActivity( Intent( this, Prueba_Simulacion::class.java ) )
        }



    }


}