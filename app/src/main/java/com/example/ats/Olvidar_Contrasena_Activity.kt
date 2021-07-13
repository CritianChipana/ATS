package com.example.ats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Olvidar_Contrasena_Activity : AppCompatActivity() {

    private lateinit var txtEmail : EditText
    private lateinit var auth:FirebaseAuth
    private lateinit var progresBar : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvidar_contrasena)

        progresBar = findViewById(R.id.progressBar4)
        txtEmail = findViewById(R.id.txtForgotPasswordEmail)
        auth = FirebaseAuth.getInstance()

    }
    fun send( view:View ){
        val email = txtEmail.text.toString()

        if( !TextUtils.isEmpty(email) ){
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener( this ){
                        task ->
                    if( task.isSuccessful ){
                        progresBar.visibility=View.VISIBLE
                        startActivity( Intent( this, LoginActivity::class.java ) )
                    }else{
                        Toast.makeText(this,"Error al enviar el email", Toast.LENGTH_LONG).show()
                    }
                }


        }

    }

}