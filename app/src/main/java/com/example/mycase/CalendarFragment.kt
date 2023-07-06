package com.example.mycase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycase.models.Category


class CalendarFragment : Fragment() {
    lateinit var selectedCategory: Category


    companion object {
        fun getInstance(category: Category):CalendarFragment{
            val calendarFragment=CalendarFragment()
            calendarFragment.selectedCategory=category
            return calendarFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }



}