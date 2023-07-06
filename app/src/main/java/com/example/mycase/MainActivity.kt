package com.example.mycase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mycase.sign_in.SignInActivity


class MainActivity : AppCompatActivity() {
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed({
            mainHomeActivity()
        }, 2000)
    }

    private fun mainHomeActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)



    }
}