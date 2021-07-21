package com.example.ats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ats.databinding.ActivityResultadosSimulacionBinding

class ResultadosSimulacion : AppCompatActivity() {

    private lateinit var binding : ActivityResultadosSimulacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResultadosSimulacionBinding.inflate( layoutInflater )
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        var bundleMaria = intent.extras

        if(  bundleMaria !=null ){
            var notita2 = bundleMaria.getString( "key_nota2" )
            println(notita2)
        }

        binding.btnsalirExamen.setOnClickListener {

            startActivity(  Intent(  this, MainActivity::class.java ) )

        }

        binding.btnVolverIntentarExamen.setOnClickListener {
            startActivity(  Intent(  this, Examen_Simulacion::class.java ) )

        }

    }
}