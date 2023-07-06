package com.example.mycase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.mycase.home_activity.HomeActivity
import com.example.mycase.models.Category


class CasesFragment : Fragment() {
    lateinit var selectedCategory: Category


    companion object {
        fun getInstance(category: Category):CasesFragment{
            val casesFragment=CasesFragment()
            casesFragment.selectedCategory=category
            return casesFragment
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        return inflater.inflate(R.layout.fragment_cases, container, false)
    }




}

