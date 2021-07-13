package com.example.ats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.ats.databinding.ActivityFrameCrearcuentaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class crear_cuenta : AppCompatActivity() {

    // variables de firebase
    private lateinit var progresBar :ProgressBar
    private lateinit var dbReference :DatabaseReference
    private lateinit var database :FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var bundle : Bundle

    // variables de datos para el registro

    // ATS
    private lateinit var txtNombre : EditText
    private lateinit var txtApellido : EditText
    private lateinit var txtEmail : EditText
    private lateinit var txtContrasena1 : EditText
    private lateinit var txtContrasena2 : EditText


    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityFrameCrearcuentaBinding.inflate( layoutInflater )
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // INSTANCIAMOS LAS VARIABLES PARA LA CONEXION CON FIREBASE
        progresBar = findViewById(R.id.progressBar_registro)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("User")
        // INSTACIAMOS LAS VARIABLES DEL USUARIO ( para almacenar sus datos en firebase )
        txtNombre = findViewById( R.id.txtNombre )
        txtApellido = findViewById( R.id.txtApellido )
        txtEmail = findViewById( R.id.txtEmail )
        txtContrasena1 = findViewById( R.id.txtContrasena1 )
        txtContrasena2 = findViewById( R.id.txtContrasena2 )
    }

    fun register( view:View ){
        createNewAccount()
    }

    private fun createNewAccount(){
        val nombre:String = txtNombre.text.toString()
        val apellido:String = txtApellido.text.toString()
        val email:String = txtEmail.text.toString()
        val contrasena1:String = txtContrasena1.text.toString()
        val contrasena2:String = txtContrasena2.text.toString()

        if( !TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(apellido) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(contrasena1) && !TextUtils.isEmpty(contrasena2) && contrasena2==contrasena1 ){
            progresBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(email,contrasena1)
                .addOnCompleteListener(this){
                        task ->
                    if ( task.isComplete ){
                        //Con esta linea puedo identificar el uid, del usuario que tiene la session iniciada
                        val user:FirebaseUser?=auth.currentUser
                      /*  uid_yo = user?.uid!!
                        //displayname_yo = user?.displayName!!
                        email_yo = user?.email!!
                        println("****************************************************************************")
                        println(uid_yo)
                        //println(displayname_yo)
                        println(email_yo)
                        println("****************************************************************************")*/
                        verifyEmail( user )

                        // REGISTRAMOS EN REALTIME DATABASE LOS DATOS DEL USUARIO

                        val userBD =dbReference.child(user?.uid!!)
                        userBD.child("NOMBRE").setValue(nombre)
                        userBD.child("APELLIDO").setValue(apellido)
                        userBD.child("EMAIL").setValue(email)
                        userBD.child("CONTRASENA").setValue(contrasena1)
                        bundle = Bundle()
                        action()
                    }else{
                        Toast.makeText(this,"No se pudo registrar, vuelva a intentar", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun action(){
        val intent = Intent(this, LoginActivity::class.java)
        /*bundle.putString("key_uid",uid_yo)
        bundle.putString("key_email",email_yo)*/
        //bundle.putString("key_nombre",displayname_yo)
        startActivity( intent.putExtras(bundle) )
    }


    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                    task ->
                if( task.isComplete ){
                    Toast.makeText(this,"Email enviado",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Error al Email enviado",Toast.LENGTH_LONG).show()

                }
            }
    }


}




