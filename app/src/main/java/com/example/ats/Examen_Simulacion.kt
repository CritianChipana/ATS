package com.example.ats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ats.databinding.ActivityExamenSimulacionBinding

class Examen_Simulacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityExamenSimulacionBinding.inflate( layoutInflater )

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerview = binding.rvUniversidades
        val adapter =  CustomAdapterExamen()
        recyclerview.layoutManager = LinearLayoutManager( this )
        recyclerview.adapter = adapter

    }



}