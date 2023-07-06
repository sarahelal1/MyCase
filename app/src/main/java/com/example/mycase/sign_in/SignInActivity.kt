package com.example.mycase.sign_in

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mycase.Data
import com.example.mycase.home_activity.HomeActivity
import com.example.mycase.register.BaseActivity
import com.example.mycase.R
import com.example.mycase.databinding.ActivitySignInBinding
import com.example.mycase.register.NewAccount
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SignInActivity :  BaseActivity<SignInViewModel, ActivitySignInBinding>(), Navigator {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        auth = FirebaseAuth.getInstance()


        subscribeToLiveData()
        showText()
    }

    private fun subscribeToLiveData() {
        viewModel.isLoadingLiveData.observe(this) {
            if (it) {
                showLoading()

            } else {
                hideLoading()
            }
        }
        viewModel.messageLiveData.observe(this) {
            val alertDialogBuilder = android.app.AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Error")
                .setMessage(it)
                .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                    }

                }).show()
        }
    }

    override fun getLayoutFile(): Int {
        return R.layout.activity_sign_in
    }

    override fun initViewModel(): SignInViewModel {
        return ViewModelProvider(this).get(SignInViewModel::class.java)
    }

    override fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun showText() {
        viewModel.isLoadingLiveDataText.observe(this)
        {
            if (it) {
                Toast.makeText(this, "Email Sent", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun navigateToRegister() {
        val intent = Intent(this, NewAccount::class.java)
        startActivity(intent)
    }


}
