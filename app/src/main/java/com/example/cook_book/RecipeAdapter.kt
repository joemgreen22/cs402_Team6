package com.example.cook_book

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter (val context: Context, var recipes: RecipeModel) : RecyclerView.Adapter<RecipeAdapter.RecipeCard>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecipeCard {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_view, parent, false)
        return RecipeCard(null, view)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(card: RecipeCard, position: Int) {
        val aRecipe = recipes[position]
        card.apply {
            recipe = aRecipe
            setup()
        }
    }

    inner class RecipeCard(var recipe: Recipe?, val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val frontSide = view.findViewById<ConstraintLayout>(R.id.front_side)
        private val backSide = view.findViewById<ConstraintLayout>(R.id.back_side)

        private val frontTxt = view.findViewById<TextView>(R.id.front_recipe_name)
        private val frontDesc = view.findViewById<TextView>(R.id.front_recipe_description)
        private val frontImg = view.findViewById<ImageView>(R.id.front_recipe_image)

        init{
            view.setOnClickListener(this)
        }

        fun setup(){
            recipe?.let {
                frontTxt.text = it.recipeName
                frontDesc.text = it.recipeDescription
                frontImg.setImageBitmap(it.image)
                //TODO: do backside as well
            }
        }

        override fun onClick(p0: View?) {
            flipView()
            recipe!!.flipped = !recipe!!.flipped
        }

        private fun flipView(){
            var animation = AnimatorSet()
            var back = AnimatorInflater.loadAnimator(context, R.animator.card_flip_back_animtion)
            var front = AnimatorInflater.loadAnimator(context, R.animator.card_flip_front_animation)

            if(recipe!!.flipped) {
                back.setTarget(frontSide)
                front.setTarget(backSide)
            } else {
                back.setTarget(backSide)
                front.setTarget(frontSide)
            }

            animation.playTogether(back, front)
            animation.start()
        }
    }
}