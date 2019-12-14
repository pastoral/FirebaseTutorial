package com.example.firebaseintro

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.UserInfo
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 123
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        createSignInIntent()

    }




    private fun createSignInIntent() {
        val providers = arrayListOf<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .setTheme(R.style.LoginUIStyle)
                .setLogo(R.drawable.auth_logo)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            var response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK){
                val user = FirebaseAuth.getInstance().currentUser
                val intent = Intent(this,MainActivity::class.java)
//                val image = user?.photoUrl
//
//                intent.putExtra("USERNAME", user?.displayName)
//                intent.putExtra("USEREMAIL",user?.email)
//                intent.putExtra("USERPHONE",user?.phoneNumber)
//                intent.putExtra("USERPROVIDER",user?.providerId)
//                intent.putExtra("USERIMAGE",user?.photoUrl)
                startActivity(intent)

            }

            else{

                if(response == null){
                    finish()
                }
                if (response?.getError()?.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    //Show No Internet Notification
                    return
                }

                if(response?.getError()?.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(this, response?.error?.errorCode.toString(), Toast.LENGTH_LONG)
                        .show()
                    Log.d("ERRORCODE", response?.error?.errorCode.toString())
                    return
                }
            }
        }
    }




}
