package com.example.mycase.register

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.mycase.MainActivity


abstract class BaseActivity<VM: ViewModel,DB:ViewDataBinding> : AppCompatActivity () {
    lateinit var viewModel: VM
    lateinit var viewDataBinding: DB
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutFile())
        viewModel = initViewModel()

    }

    abstract fun getLayoutFile(): Int
    abstract fun initViewModel(): VM
    fun showError(title: String = "", message: String = "") {
        val alertDialogBuilder = android.app.AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0!!.dismiss()
                }

            }).show()
    }

    fun showLoading() {
        progressDialog.setMessage("Loading")
        progressDialog.show()
    }

    fun hideLoading() {
        progressDialog.dismiss()
    }

}

