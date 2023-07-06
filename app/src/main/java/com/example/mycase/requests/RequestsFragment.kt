package com.example.mycase.requests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycase.CalendarFragment
import com.example.mycase.R
import com.example.mycase.models.Category


class RequestsFragment : Fragment() {
    lateinit var selectedCategory: Category
    companion object {
        fun getInstance(category: Category): RequestsFragment {
            val requestsFragment= RequestsFragment()
            requestsFragment.selectedCategory=category
            return requestsFragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }



}