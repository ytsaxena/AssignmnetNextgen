package com.sachin.mvvmapi.LoginScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.sachin.mvvmapi.RepoListScreen.UI.MainActivity
import com.sachin.mvvmapi.R
import com.sachin.mvvmapi.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("435115285875-00vb0bl6rdbb9q5empe5ifb7smm20qtf.apps.googleusercontent.com") // from google-services.json
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this@LoginActivity, gso)
        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            val user = firebaseAuth.currentUser
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.putExtra("USER", user?.uid.toString())
            intent.putExtra("NAME", user?.displayName.toString())
            intent.putExtra("EMAIL", user?.email.toString())
            startActivity(intent)
            finish()
            return
        }






        binding.btnSignIn.setOnClickListener{

            signIn()
        }



    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            Log.d("SignIn", "data: ${data?.extras}")

            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("SignIn", "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Log.d("Auth", "signInWithCredential:success - ${user?.email}")
                    Toast.makeText(this@LoginActivity,"Login Successfully",Toast.LENGTH_SHORT).show()
                 //   val user = FirebaseAuth.getInstance().currentUser
                    val name = user?.displayName
                    val email = user?.email
                    val photoUrl = user?.photoUrl
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("USER",user?.uid.toString())
                    intent.putExtra("NAME",name.toString())
                    intent.putExtra("EMAIL",email.toString())
                    startActivity(intent)
                    finish()

                } else {
                    Log.w("Auth", "signInWithCredential:failure", task.exception)
                }
            }
    }



}



