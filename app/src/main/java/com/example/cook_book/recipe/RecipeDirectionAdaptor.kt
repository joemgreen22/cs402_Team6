package com.example.cook_book.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cook_book.R

class RecipeDirectionAdaptor(var directions: List<String>) : RecyclerView.Adapter<RecipeDirectionAdaptor.Line>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Line {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_view, parent, false)
        return Line(view)
    }

    override fun onBindViewHolder(line: Line, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class Line(val view: View) : RecyclerView.ViewHolder(view) {

    }
}