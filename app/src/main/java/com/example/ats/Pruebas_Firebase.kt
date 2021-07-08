package com.example.ats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ats.databinding.ActivityPruebasFirebaseBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Pruebas_Firebase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityPruebasFirebaseBinding.inflate(layoutInflater)
        setContentView( binding.root )
        super.onCreate(savedInstanceState)

        binding.btnFirebase.setOnClickListener(){
            val database = Firebase.database
            val myRef = database.getReference("message")

            myRef.setValue("Hello, World!")
        }

    }
}