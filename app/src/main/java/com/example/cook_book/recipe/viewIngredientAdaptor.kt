package com.example.cook_book.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cook_book.R
import kotlin.math.ceil

class viewIngredientAdaptor (var ingredients: List<String>) : RecyclerView.Adapter<viewIngredientAdaptor.Line>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewIngredientAdaptor.Line {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ingredient_entry, parent, false)
        return Line(view)
    }

    override fun onBindViewHolder(line: viewIngredientAdaptor.Line, position: Int) {
        line.apply {
            ingredientL.text = ingredients[position]
        }
    }

    override fun getItemCount(): Int = ingredients.size

    inner class Line(val view: View) : RecyclerView.ViewHolder(view) {
        val ingredientL = view.findViewById<TextView>(R.id.ingredient_text)

    }






}