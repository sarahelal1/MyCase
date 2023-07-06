package com.example.mycase.home_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycase.R
import com.example.mycase.models.Category

class CategoriesAdapter(var items:List<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View?

            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.left_sided_category, parent, false)

        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.categorytitle.text = item.title.toString()
        holder.categoryImage.setImageResource(item.imageId)
        holder.itemView.setOnClickListener {
            onItemClicked?.onCategoryClicked(position, item)
        }
    }

    var onItemClicked: OnItemClicked? = null

    interface OnItemClicked {
        fun onCategoryClicked(pos: Int, item: Category)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categorytitle = itemView.findViewById<TextView>(R.id.category_title)
        val categoryImage = itemView.findViewById<ImageView>(R.id.category_image)

    }
}