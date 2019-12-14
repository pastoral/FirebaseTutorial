package com.example.firebaseintro

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.firebaseintro.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    val auth = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        if(auth!=null && intent!= null){

            createUI()
        }
        else{
            startActivity(Intent(this,LoginActivity::class.java))
            this.finish()
        }

    }

    override fun onResume() {
        super.onResume()
        if(auth!=null&& intent!= null){

            createUI()
        }
        else{
            startActivity(Intent(this,LoginActivity::class.java))
            this.finish()

        }
    }

    fun createUI(){

        val list = auth?.providerData
        var providerData:String = ""
        list?.let {
            for(provider in list){
                providerData = providerData+ " " +provider.providerId
            }
        }

        auth?.let {
        txtName.text = auth.displayName
        txtEmail.text = auth.email
        txtPhone.text = auth.phoneNumber
        txtProvider.text = providerData
            Glide
                .with(this)
                .load(auth.photoUrl)
                .fitCenter()
                    .placeholder(R.drawable.profilepic)
                .into(profile_image)



        }




        btnLogOut.setOnClickListener{
            AuthUI.getInstance().signOut(this)
                .addOnSuccessListener {
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
//                    this.finish()
                    Toast.makeText(this, "Successfully Log Out", Toast.LENGTH_SHORT).show()
                }

        }
    }


//    fun generateFBKey(){
//        try {
//            val info = packageManager.getPackageInfo(
//                "com.example.firebaseintro",
//                PackageManager.GET_SIGNATURES
//            )
//            for (signature in info.signatures) {
//                val md: MessageDigest = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
//            }
//        } catch (e: PackageManager.NameNotFoundException) {
//        } catch (e: NoSuchAlgorithmException) {
//        }
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }




}
