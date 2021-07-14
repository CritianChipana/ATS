package com.example.ats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.example.ats.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var progresBar : ProgressBar
    private lateinit var auth: FirebaseAuth

    private lateinit var dbReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityMainBinding.inflate( layoutInflater )
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        auth = FirebaseAuth.getInstance()
        dbReference = FirebaseDatabase.getInstance().getReference()
        val user: FirebaseUser?=auth.currentUser


        val uidTi = user?.uid!!
        dbReference.child("User").child(uidTi).get().addOnSuccessListener {

            println("**************************************************")
            val nombreMain = it.child("NOMBRE").getValue()
            val ApellidoMain = it.child("APELLIDO").getValue()

            println("Nombre:$nombreMain")
            println("Nombre:$ApellidoMain")
            binding.mainNombre.setText("$nombreMain, $ApellidoMain")
            println("**************************************************")
            Log.i("firebase", "Got value ${it.value}")
            println("**************************************************")

        }.addOnFailureListener {

            println("**************************************************")
            Log.e("firebase", "Error getting data", it)
            println("**************************************************")

        }

        // TODO LO DE ARRIBA FUE PARA PROBAR SIIIIII
        binding.btnRendirExamne.setOnClickListener(){
            startActivity( Intent( this, Examen_Simulacion::class.java  ))
        }
        binding.btnRendirPractica.setOnClickListener(){
            startActivity( Intent( this, Prueba_Simulacion::class.java  ))

        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser == null){
            NoHayUsuarioLogeado();
        }
    }


    fun sessionClose(view: View){
        closeSession()
    }

    private fun closeSession(){
        auth.signOut()
        startActivity( Intent( this, LoginActivity::class.java ) )
    }

    private fun NoHayUsuarioLogeado(){
        startActivity( Intent( this, LoginActivity::class.java ) )
    }



}