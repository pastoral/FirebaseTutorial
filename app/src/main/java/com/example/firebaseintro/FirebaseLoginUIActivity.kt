package com.example.firebaseintro

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class FirebaseLoginUIActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_login_ui)
    }

//    private fun createSignInIntent() {
//        val providers = arrayListOf<AuthUI.IdpConfig>(
//            AuthUI.IdpConfig.EmailBuilder().build(),
//            AuthUI.IdpConfig.FacebookBuilder().build(),
//            AuthUI.IdpConfig.PhoneBuilder().build(),
//            AuthUI.IdpConfig.GoogleBuilder().build()
//        )
//        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), RC_SIGN_IN)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode == RC_SIGN_IN){
//            var response = IdpResponse.fromResultIntent(data)
//            if (resultCode == Activity.RESULT_OK){
//                val user = FirebaseAuth.getInstance().currentUser
//            }
//            else{
//                Toast.makeText(this,response?.error?.errorCode.toString(),Toast.LENGTH_LONG).show()
//            }
//        }
//    }
}
