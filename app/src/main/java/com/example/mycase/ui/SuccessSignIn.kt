package com.example.mycase.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mycase.home_activity.HomeActivity
import com.example.mycase.R
import com.example.mycase.databinding.ActivitySuccessSignInBinding

class SuccessSignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySuccessSignInBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_success_sign_in)
        binding.goToHomePage.setOnClickListener{
            val intnent = Intent(this, HomeActivity::class.java)
            startActivity(intnent)
        }



    }
}