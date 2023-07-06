package com.example.mycase.register

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycase.firebase.addToFireStore
import com.example.mycase.models.AppUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class NewAccountViewModel: ViewModel() {
    val name = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val national_num = ObservableField<String>()
    val card_num = ObservableField<String>()
    val nameError = ObservableField<String?>()
    val card_num_Error = ObservableField<String?>()
    val emailError = ObservableField<String?>()
    val passwordError = ObservableField<String?>()
    val national_num_Error = ObservableField<String?>()
    val messageLiveData= MutableLiveData<String>()
    val isLoadingLiveData= MutableLiveData<Boolean>()
    var navigator:Navigator?=null
    val auth = Firebase.auth
    fun createAccount() {
        if (validate()) {
            isLoadingLiveData.value=true
            auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
                .addOnCompleteListener { task ->
                    isLoadingLiveData.value=false
                    if (!task.isSuccessful) {

                        Log.e("createAccount",  task.exception.toString())
                        messageLiveData.value=task.exception!!.message

                    } else {
                        val user: AppUser = AppUser(
                            name = name.get(),
                            email = email.get(),
                            id = task.result.user?.uid
                        )
                        navigator?.navigateToHome()
                        addToFireStore(user, successListener = {
                            isLoadingLiveData.value = false
                        }) {
                            isLoadingLiveData.value = false
                            messageLiveData.value = it.message
                        }

                        Log.e("createAccount",  "createdAccount")
                    }
                }
        }
    }


    fun validate(): Boolean {
        var valid = true
        if (name.get().isNullOrBlank()) {
            valid = false
            nameError.set("please enter valid name")
        }else{
            nameError.set(null)
        }
        if (card_num.get().isNullOrBlank()) {
            valid = false
            card_num_Error.set("please enter valid name")
        }else{
           card_num_Error.set(null)
        }
        if (email.get().isNullOrBlank()) {
            valid = false
            emailError.set("please enter valid email")
        }else{
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()) {
            valid = false
            passwordError.set("please enter valid password")
        }else{
            passwordError.set(null)
        }
        if (national_num.get().isNullOrBlank()) {
            valid = false
            national_num_Error.set("please enter valid password")
        }else{
            national_num_Error.set(null)
        }

        return valid
    }

}