package com.example.ats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ats.databinding.ActivityPruebaSimulacionBinding

class Prueba_Simulacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityPruebaSimulacionBinding.inflate( layoutInflater )

        super.onCreate(savedInstanceState)
        //PARA OCULTAR LA BARRA DE NAVEGACION
        this.supportActionBar?.hide()
        setContentView(binding.root)

        val recyclerView = binding.rvCursos
        val adapter = CustomAdapter()

        recyclerView.layoutManager = LinearLayoutManager( this )
        recyclerView.adapter = adapter

    }
}