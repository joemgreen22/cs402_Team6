package com.example.cook_book

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

public class recipeAdapter (context: Context, var recipe: ArrayList<recipeCard>)
    : RecyclerView.Adapter<recipeAdapter.recipeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : recipeAdapter.recipeHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_view, parent, false)
        return recipeHolder(view)
    }

    override fun getItemCount() = recipe.size

    override fun onBindViewHolder(holder: recipeHolder, position: Int) {
        val aRecipe = recipe[position]
        holder.apply {
            titleTextView.text = aRecipe.name
            var sscolor = "#F0FFFF"
            kSelect = aRecipe.selected
            if (kSelect) {
                sscolor = "#59f9f0"
            }
            titleTextView.setBackgroundColor(Color.parseColor(sscolor))
        }
    }

    inner class recipeHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val titleTextView: TextView = view.findViewById(R.id.recipe_name)
        var kSelect: Boolean = false

        init {
            itemView.setOnClickListener(this)
        }

        //we need to add code to mark this item as selected.
        override fun onClick(v: View) {
            var apos = getBindingAdapterPosition()
            kSelect = recipe[apos].selected
            kSelect = !kSelect
            recipe[apos].selected = kSelect


            var sscolor = "#F0FFFF"
            if (kSelect) {
                sscolor = "#59f9f0"
            }
            titleTextView.setBackgroundColor(Color.parseColor(sscolor))
        }
    }


}