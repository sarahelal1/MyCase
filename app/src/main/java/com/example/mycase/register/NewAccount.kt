package com.example.mycase.register

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mycase.R
import com.example.mycase.ui.SuccessSignIn
import com.example.mycase.databinding.ActivityNewAccountBinding

class NewAccount : BaseActivity<NewAccountViewModel, ActivityNewAccountBinding>(),Navigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm=viewModel
        viewModel.navigator=this
        subscribeToLiveData()


    }

    private fun subscribeToLiveData() {
        viewModel.isLoadingLiveData.observe(this){
            if(it){
                showLoading()

            }else{
                hideLoading()
            }
        }
        viewModel.messageLiveData.observe(this){
            val alertDialogBuilder=android.app.AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Error")
                .setMessage(it)
                .setPositiveButton("OK",object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                    }

                }).show()
        }
    }

    override fun getLayoutFile(): Int {
        return R.layout.activity_new_account
    }

    override fun initViewModel(): NewAccountViewModel {
        return ViewModelProvider(this).get(NewAccountViewModel::class.java)
    }

    override fun navigateToHome() {
        val intent= Intent(this, SuccessSignIn::class.java)
        startActivity(intent)
    }


}