package com.example.cook_book.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cook_book.R
import com.example.cook_book.Recipe
import kotlin.math.ceil

class RecipeIngredientsAdaptor(var ingredients: List<String>) : RecyclerView.Adapter<RecipeIngredientsAdaptor.Line>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Line {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_entry, parent, false)
        return Line(view)
    }

    override fun onBindViewHolder(line: Line, position: Int) {
        val loc = position * 2
        line.apply {
            if(ingredients.size == loc + 1){
                rightSide.visibility = View.GONE
                leftSideText.text = ingredients[loc]
            }else{
                leftSideText.text = ingredients[loc]
                rightSideText.text = ingredients[loc + 1]
            }
        }
    }

    override fun getItemCount(): Int = ceil(ingredients.size / 2.0).toInt()

    inner class Line(val view: View) : RecyclerView.ViewHolder(view) {
        val rightSide = view.findViewById<LinearLayout>(R.id.right_side)

        val leftSideText = view.findViewById<TextView>(R.id.left_text)
        val rightSideText = view.findViewById<TextView>(R.id.right_text)
    }
}