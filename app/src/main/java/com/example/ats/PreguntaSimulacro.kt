package com.example.ats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ats.databinding.ActivityFrame11Binding
import com.example.ats.databinding.ActivityPreguntaSimulacroBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PreguntaSimulacro : AppCompatActivity() {

    private lateinit var dbReference : DatabaseReference
    private lateinit var binding : ActivityPreguntaSimulacroBinding
    private lateinit var identiicador : String
    private lateinit var materia : String
    private var listaPreguntas = mutableListOf<String>()
    private var listaRespuestas = mutableListOf<String>()
    private var listaAlternativas = mutableListOf<String>()
    private var respuestas = mutableListOf<String>()
    var cont = 1
    var contRespuesta = 0
    var respuestascorrectas = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityPreguntaSimulacroBinding.inflate( layoutInflater )

        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        dbReference = FirebaseDatabase.getInstance().getReference()
        val bundleReception2 = intent.extras

        if(  bundleReception2 != null ){
            identiicador = bundleReception2!!.getString( "key_uni" ).toString()
            println( identiicador )
        } else {
            println("VAMOS MAL DICES1")
            println("VAMOS MAL DICES2")
            println("VAMOS MAL DICES3")
            println("VAMOS MAL DICES4")
        }

        materia = when(identiicador){
            "0" -> "UNMSM"
            "1" -> "UNTELS"
            "2" -> "UNI"
            "3" -> "UNALM"
            "4" -> "UNFV"
            else ->"No hay datos"
        }

        // CAPTURAR LOS DATOS DE CADA PREGUNTA
        dbReference.child("Practica").child(materia).get().addOnSuccessListener {

            //binding.txtCursoPrueba.setText( materia )

            var pregunta1enunciado = it.child("problema 1").child("problema").getValue().toString()
            var pregunta1solucion = it.child("problema 1").child("solucion").getValue().toString()
            var pregunta2enunciado = it.child("problema 2").child("problema").getValue().toString()
            var pregunta2solucion = it.child("problema 2").child("solucion").getValue().toString()
            var pregunta3enunciado = it.child("problema 3").child("problema").getValue().toString()
            var pregunta3solucion = it.child("problema 3").child("solucion").getValue().toString()
            var pregunta4enunciado = it.child("problema 4").child("problema").getValue().toString()
            var pregunta4solucion = it.child("problema 4").child("solucion").getValue().toString()

            listaPreguntas = mutableListOf<String>(
                pregunta1enunciado ,
                pregunta2enunciado,
                pregunta3enunciado,
                pregunta4enunciado
            )

            listaRespuestas = mutableListOf(
                pregunta1solucion,
                pregunta2solucion,
                pregunta3solucion,
                pregunta4solucion
            )

            binding.txtPreguntaSimulacion.setText( pregunta1enunciado)
            ListarAlternativas( "problema 1" )
            println( "333333333333 3333333333 ----------- niñotes" )
            println( listaAlternativas )

            if( binding.radioButtonE1.isChecked ){
                respuestas[contRespuesta]="A"
            } else if ( binding.radioButtonE2.isChecked ) {
                respuestas[contRespuesta]="B"
            } else if ( binding.radioButtonE3.isChecked ) {
                respuestas[contRespuesta]="C"
            } else if ( binding.radioButtonE4.isChecked ) {
                respuestas[contRespuesta]="D"
            } else if ( binding.radioButtonE5.isChecked ) {
                respuestas[contRespuesta]="E"
            }

            println("111111111111111111111111111111111111111111111111111111111112")
            println("111111111111111111111111111111111111111111111111111111111113")
            println("111111111111111111111111111111111111111111111111111111111114")
            println("111111111111111111111111111111111111111111111111111111111115")

        }.addOnFailureListener {

            println("**************************************************")
            Log.e("firebase", "Error getting data", it)
            println("**************************************************")

        }

        // funciones click para el desplazo de las preguntas

        binding.btnSiguiente.setOnClickListener(){

            if ( cont < 4 ){
                binding.txtPreguntaSimulacion.setText(listaPreguntas[cont])
                cont = cont +1
                contRespuesta ++
                if( binding.radioButtonE1.isChecked ){
                    respuestas.add("A")
                } else if ( binding.radioButtonE2.isChecked ) {
                    respuestas.add("B")
                } else if ( binding.radioButtonE3.isChecked ) {
                    respuestas.add("C")
                } else if ( binding.radioButtonE4.isChecked ) {
                    respuestas.add("D")
                } else if ( binding.radioButtonE5.isChecked ) {
                    respuestas.add("E")
                }else{
                    respuestas.add("F")
                }

                if(  cont==9 ){

                }


            } else {
                for( (i, value) in listaAlternativas.withIndex() ){
                    for ( ( i, value2 ) in respuestas.withIndex() ){
                        if( value ==  value2 ){
                            respuestascorrectas ++
                        }
                    }

                }
                var bundleAnwer = Bundle()
                var anynote = respuestascorrectas.toString()
                bundleAnwer.putString(  "key_nota2",anynote )
                var intentNota = Intent (  this , ResultadosSimulacion::class.java)
                startActivity( intentNota.putExtras( bundleAnwer ) )
            }
            println("4444444444444444")
            println(cont)
            ListarAlternativas( "problema ${cont}" )
            var nose =binding.radioGroupExamen.getChildAt(0)
            binding.radioGroupExamen.check( nose.getId() )

        }

        /*     binding.cpatrasBtn.setOnClickListener(){

                 //if ( cont != 0 ){
                     cont = cont - 1

                     binding.txtPregunta.setText(listaPreguntas[cont])
                     ListarAlternativas( "problema ${cont}" )

                 //} else {

                 //    startActivity( Intent( this, frame_10::class.java ) )
                  //   startActivity( Intent( this, Prueba_Simulacion::class.java ) )

                 //}
             }*/

    }

    // funcion para listar las alternativas de las preguntas
    fun ListarAlternativas(numeropregunta:String): MutableList<String> {

        var istaAlternativas = mutableListOf<String>()
        // CAPTURAR LOS DATOS DE CADA PREGUNTA
        dbReference.child("Practica").child("Aritmetica").child(numeropregunta).child("alternativas").get().addOnSuccessListener {

            var alternativaA = it.child("1").getValue().toString()
            var alternativaB = it.child("2").getValue().toString()
            var alternativaC = it.child("3").getValue().toString()
            var alternativaD = it.child("4").getValue().toString()
            var alternativaE = it.child("5").getValue().toString()

            println("2222 estasmos fuertes 222222")
            listaAlternativas = mutableListOf<String>(
                alternativaA,
                alternativaB,
                alternativaC,
                alternativaD,
                alternativaE
            )

            println( "555555555555555555555aa arbol" )
            println(  listaAlternativas)
            binding.radioButtonE1.setText( listaAlternativas[0] )
            binding.radioButtonE2.setText( listaAlternativas[1] )
            binding.radioButtonE3.setText( listaAlternativas[2] )
            binding.radioButtonE4.setText( listaAlternativas[3] )
            binding.radioButtonE5.setText( listaAlternativas[4] )


        }.addOnFailureListener {

            println("**************************************************")
            Log.e("firebase", "Error getting data", it)
            println("**************************************************")

        }
        return istaAlternativas
    }

}