package com.example.mycase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycase.models.Category


class PaymentsFragment : Fragment() {
    lateinit var selectedCategory: Category

    companion object {
        fun getInstance(category: Category):PaymentsFragment{
            val paymentsFragment=PaymentsFragment()
            paymentsFragment.selectedCategory=category
            return paymentsFragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payments, container, false)
    }

}

