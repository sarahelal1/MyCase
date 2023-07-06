package com.example.mycase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mycase.databinding.ActivityHomeBinding
import com.example.mycase.databinding.ActivitySettingsBinding
import com.example.mycase.sign_in.SignInActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsActivity : AppCompatActivity() {
    val auth = Firebase.auth.currentUser
    lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()


    }
    fun initViews(){
        binding.btnDelete.setOnClickListener {
            auth?.delete()?.addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this,"Account deleted successfully",Toast.LENGTH_SHORT).show()
                    val intent= Intent(this,SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Log.e("error",it.exception.toString())
                }
            }


        }
    }
}