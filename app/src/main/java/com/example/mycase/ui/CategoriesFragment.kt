package com.example.mycase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

import com.example.mycase.R
import com.example.mycase.home_activity.CategoriesAdapter
import com.example.mycase.models.Category


class CategoriesFragment : Fragment() {
    lateinit var categoriesRecyclerView: RecyclerView
    val categories= listOf<Category>(
        Category(id="cases",title="القضايا",
            imageId = R.drawable.justice),
        Category(id="general",title="المواعيد",
            imageId = R.drawable.schedule),
        Category(id="health",title= "المدفوعات",
            imageId = R.drawable.profits),
        Category(id="business",title="الطلبات",
            imageId = R.drawable.request),
        Category(id="entertainment",title="الموكلون",
            imageId = R.drawable.customer
        )
    )
    lateinit var adapter: CategoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){
        categoriesRecyclerView=requireView().findViewById(R.id.categories_recycler_view)
        adapter= CategoriesAdapter(categories)
        adapter.onItemClicked=object: CategoriesAdapter.OnItemClicked{
            override fun onCategoryClicked(pos: Int, item: Category) {
                onCategoryClicked?.onCategory(item)
            }

        }

        categoriesRecyclerView.adapter=adapter
    }
    var onCategoryClicked:OnCategoryClicked?=null
    interface OnCategoryClicked{
        fun onCategory(category: Category)
    }
}



