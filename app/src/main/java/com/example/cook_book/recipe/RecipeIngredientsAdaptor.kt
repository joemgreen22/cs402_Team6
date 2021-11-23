package com.example.cook_book.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cook_book.R
import com.example.cook_book.RecipeAdapter
import kotlin.math.ceil
import kotlin.reflect.KFunction2

class RecipeIngredientsAdaptor(val onClickListener: View.OnClickListener, var ingredients: List<String>) : RecyclerView.Adapter<RecipeIngredientsAdaptor.Line>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Line {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_entry, parent, false)
        return Line(view)
    }

    override fun onBindViewHolder(line: Line, position: Int) {
        val loc = position * 3
        line.apply {
            first.alpha = 1.0f
            second.alpha = 1.0f
            third.alpha = 1.0f

            if(loc + 3 <= ingredients.size) {
                firstText.text = ingredients[loc]
                secondText.text = ingredients[loc + 1]
                thirdText.text = ingredients[loc + 2]
            }else if (loc + 2 == ingredients.size){
                firstText.text = ingredients[loc]
                secondText.text = ingredients[loc + 1]
                third.alpha = 0.0f
            }else{
                firstText.text = ingredients[loc]
                second.alpha = 0.0f
                third.alpha = 0.0f
            }
        }
    }

    override fun getItemCount(): Int = ceil(ingredients.size / 3.0).toInt()

    inner class Line(val view: View) : RecyclerView.ViewHolder(view) {
        val first = view.findViewById<LinearLayout>(R.id.first_line)
        val firstText = first.findViewById<TextView>(R.id.ingredient_text)
        val second = view.findViewById<LinearLayout>(R.id.second_line)
        val secondText = second.findViewById<TextView>(R.id.ingredient_text)
        val third = view.findViewById<LinearLayout>(R.id.third_line)
        val thirdText = third.findViewById<TextView>(R.id.ingredient_text)

            init {
            view.setOnClickListener(onClickListener)
        }
    }
}