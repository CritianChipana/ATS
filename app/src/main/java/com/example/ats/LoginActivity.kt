package com.example.ats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.ats.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100

    private lateinit var txtUser : EditText
    private lateinit var txtPassword : EditText
    private lateinit var progresBar : ProgressBar
    private lateinit var auth: FirebaseAuth

    private lateinit var database : FirebaseDatabase
    private lateinit var dbReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityLoginBinding.inflate( layoutInflater )
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        dbReference = database.reference.child("User")

        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtLogPassword)
        progresBar = findViewById(R.id.progressBar_login)
        auth = FirebaseAuth.getInstance()
        var user: FirebaseUser?=auth.currentUser

/*
        val bundleRecepcion= intent.extras
        if (bundleRecepcion != null){
            val uid_reception = bundleRecepcion!!.getString("key_uid")
            val email_reception = bundleRecepcion!!.getString("key_email")
            //val nombre_reception = bundleRecepcion!!.getString("key_nombre")
            binding.txtUid.setText(uid_reception)
            binding.txtemailreception.setText(email_reception)
        }
*/
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            action();
        }
    }

    fun forgotpassword(view:View){
        startActivity( Intent( this, Olvidar_Contrasena_Activity::class.java ) )
    }

    fun register(view:View){
        startActivity( Intent( this, crear_cuenta::class.java ) )
    }

    fun login(view:View){
        loginUser()
    }

    fun loginUser(){
        val user:String = txtUser.text.toString()
        val password :String = txtPassword.text.toString()

        if( !TextUtils.isEmpty(user) && !TextUtils.isEmpty(password) ){

            progresBar.visibility = View.VISIBLE

            try {
                auth.signInWithEmailAndPassword( user,password )
                    .addOnCompleteListener( this ){
                            task ->

                        if( task.isSuccessful ){
                            action()
                            progresBar.visibility = View.GONE
                        }else{
                            progresBar.visibility = View.GONE
                            Toast.makeText(this, "Error en la autenticacion", Toast.LENGTH_LONG).show()
                        }
                    }
            }catch ( e : Exception){
                println("error en $e")
            }
        }
    }

    private fun action(){
        startActivity( Intent( this, MainActivity::class.java ) )
    }

    // ************************  INGRESAR A LA APP CON GOOGLE  ************************

    fun LoginWitGoogle( view:View ){
        val googleConf = GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
            .requestIdToken( getString( R.string.default_web_client_id ) )
            .requestEmail()
            .build()
        val googleClient:GoogleSignInClient = GoogleSignIn.getClient( this,googleConf )
        googleClient.signOut()
        startActivityForResult( googleClient.signInIntent, GOOGLE_SIGN_IN )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if( requestCode  == GOOGLE_SIGN_IN ) {
            val task = GoogleSignIn.getSignedInAccountFromIntent( data )

            try {
                val account = task.getResult( ApiException::class.java )

                if( account != null ){

                    val credencial = GoogleAuthProvider.getCredential(  account.idToken, null )
                    println( "***********************************************************" )
                    println("displayname "+ account.displayName )
                    println("email "+ account.email )
                    println("id "+ account.id )
                    println( "idtoken "+account.idToken )
                    println( "family name "+account.familyName )
                    println( "account "+account.account )
                    println( "ur photo "+account.photoUrl )
                    println("account a secas :v "+account)
                    println( "***********************************************************" )

                    auth.signInWithCredential(credencial).addOnCompleteListener{
                        if ( it.isSuccessful ){
                            var user: FirebaseUser?=auth.currentUser
                            println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
                            //REGISTRAMOS LOS DATOS DE GOOGLE A REALTIME DATABASE
                            val userBD =dbReference.child(user?.uid!!)
                            userBD.child("NOMBRE").setValue( account.givenName )
                            userBD.child("APELLIDO").setValue(account.familyName)
                            userBD.child("EMAIL").setValue(account.email)
                            userBD.child("CONTRASENA").setValue(account.id)
                            println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
                            action()
                        } else {
                            Toast.makeText(this, "Error en la autenticacion, LINEA 134", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }catch ( e:ApiException ){
                println( "error en ->>>>>>>>>>  $e" )
            }
        }
    }

// ********************************************* REGISTRARSE CON FACEBOOK



}