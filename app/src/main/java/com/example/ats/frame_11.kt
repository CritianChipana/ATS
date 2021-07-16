package com.example.ats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ats.databinding.ActivityFrame11Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class frame_11 : AppCompatActivity() {

    private lateinit var dbReference : DatabaseReference

    private lateinit var binding : ActivityFrame11Binding
    private lateinit var identiicador : String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFrame11Binding.inflate( layoutInflater )
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        dbReference = FirebaseDatabase.getInstance().getReference()

        val bundleReception2 = intent.extras


        if(  bundleReception2 != null ){

            identiicador = bundleReception2!!.getString( "key_curso" ).toString()
            println( "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" )

            println( identiicador )
            println( "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" )


        } else {
            println("VAMOS MAL DICES1")
            println("VAMOS MAL DICES2")
            println("VAMOS MAL DICES3")
            println("VAMOS MAL DICES4")
        }

        var materia :String = when(identiicador){
            "0" -> "Aritmetica"
            "1" -> "Geometria"
            "2" -> "Trigonometria"
            else ->"No hay datos"
        }

        // CAPTURAR LOS DATOS DE CADA PREGUNTA
        println("9999999999999999999999999999999999999")
        println(materia)
        dbReference.child("Practica").child(materia).get().addOnSuccessListener {

            println("**************************************************")
            println("11111111111111111111111111111111111111111111111111111111111")
            val pregunta = it.child("problema 1").child("problema").getValue()
            //val preguntas:List<> = it.children("pro")
            val solucion = it.child("APELLIDO").getValue()
            println( it )
            println( pregunta )
            println("11111111111111111111111111111111111111111111111111111111111")

            // CAPTURAMOS LOS DATOS EN UNA CLASE
            




/*
            println("Nombre:$nombreMain")
            println("Nombre:$ApellidoMain")
            binding.mainNombre.setText("$nombreMain, $ApellidoMain")
            println("**************************************************")
            Log.i("firebase", "Got value ${it.value}")
            println("**************************************************")
*/
        }.addOnFailureListener {

            println("**************************************************")
            Log.e("firebase", "Error getting data", it)
            println("**************************************************")


        }





        binding.btnSiguiente.setOnClickListener(){

        }

        binding.cpatrasBtn.setOnClickListener(){

        }

    }



}