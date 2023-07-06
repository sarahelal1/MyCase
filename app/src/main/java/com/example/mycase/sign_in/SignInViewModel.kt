package com.example.mycase.sign_in

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.mycase.firebase.getUserFromFireStore
import com.example.mycase.models.AppUser
import com.example.mycase.Data
import com.example.mycase.home_activity.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.ktx.Firebase

import java.lang.Exception

class SignInViewModel: ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()

    val emailError = ObservableField<String?>()
    val passwordError = ObservableField<String?>()
    val messageLiveData = MutableLiveData<String>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveDataText = MutableLiveData<Boolean>()
    var navigator: Navigator? = null
    val auth = Firebase.auth
    fun signIn() {
        if (validate()) {
            isLoadingLiveData.value = true
            auth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
                .addOnCompleteListener { task ->
                    isLoadingLiveData.value = false
                    if (!task.isSuccessful) {
                        Log.e("Sign in", task.exception.toString())
                        messageLiveData.value = task.exception!!.message
                    } else {
                        getUserFromFireStore(task.result.user?.uid ?: "",
                            onSuccess = object : OnSuccessListener<DocumentSnapshot> {
                                override fun onSuccess(docSnapshot: DocumentSnapshot?) {
                                    val currentUser = docSnapshot?.toObject(AppUser::class.java)
                                    Data.currentUser=currentUser
                                    navigator?.navigateToHome()
                                }

                            },
                            onFailure = object : OnFailureListener {
                                override fun onFailure(p0: Exception) {
                                    isLoadingLiveData.value = false
                                    messageLiveData.value = p0.message
                                }
                            }
                        )
                        Log.e("Sign in", "log in successful")
                    }
                }
        }
    }



    fun validate(): Boolean {
        var valid = true

        if (email.get().isNullOrBlank()) {
            valid = false
            emailError.set("please enter valid email")
        } else {
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()) {
            valid = false
            passwordError.set("please enter valid password")
        } else {
            passwordError.set(null)
        }
        return valid
    }

    fun navigateToRegisterScreen() {
        navigator?.navigateToRegister()
    }

    fun forgetPass(){
        auth.sendPasswordResetEmail(email.get()!!)
            .addOnSuccessListener{
                isLoadingLiveDataText.value=true
                Log.e("Sign in", "Email Sent")
            }
            .addOnFailureListener{
                isLoadingLiveDataText.value=false
                Log.e("Sign in", "Error")

            }
    }



}
