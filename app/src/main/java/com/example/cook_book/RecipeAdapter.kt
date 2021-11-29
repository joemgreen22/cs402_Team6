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
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cook_book.recipe.RecipeDirectionAdaptor
import com.example.cook_book.recipe.RecipeIngredientsAdaptor

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

        // Front Components
        private val frontTxt = view.findViewById<TextView>(R.id.front_recipe_name)
        private val frontDesc = view.findViewById<TextView>(R.id.front_recipe_description)
        private val frontImg = view.findViewById<ImageView>(R.id.front_recipe_image)

        // Back Components
        private var recycler = view.findViewById<RecyclerView>(R.id.recipe_back_recycler)
        private val ingredientView = view.findViewById<LinearLayout>(R.id.ingredients)
        private val ingredientsBar = view.findViewById<ImageView>(R.id.ingredient_underline)
        private val directionView = view.findViewById<LinearLayout>(R.id.directions)
        private val directionBar = view.findViewById<ImageView>(R.id.direction_underline)

        init{
            view.setOnClickListener(this)

            ingredientView.visibility = View.GONE
            directionView.visibility = View.GONE
            recycler.visibility = View.GONE

            ingredientView.setOnClickListener {
                if(!recipe!!.ingredientsSelect) {
                    recipe!!.ingredientsSelect = !recipe!!.ingredientsSelect
                    setupTab()
                }
            }

            directionView.setOnClickListener {
                if(recipe!!.ingredientsSelect) {
                    recipe!!.ingredientsSelect = !recipe!!.ingredientsSelect
                    setupTab()
                }
            }
        }

        fun setup(){
            recipe?.let {
                frontTxt.text = it.recipeName
                frontDesc.text = it.recipeDescription
                frontImg.setImageBitmap(it.image)
                setupTab()

                if(!it.flipped){
                    ingredientView.visibility = View.GONE
                    directionView.visibility = View.GONE
                    recycler.visibility = View.GONE
                    backSide.alpha = 0.0F
                    frontSide.alpha = 1.0F
                }else{
                    frontSide.alpha = 0.0F
                    backSide.alpha = 1.0F
                }
            }
        }

        override fun onClick(p0: View?) {
            flipView()
            recipe!!.flipped = !recipe!!.flipped

            // Reset Back Side
            recipe!!.ingredientsSelect = true
            setupTab()
        }

        private fun flipView(){
            if(!recipe!!.flipped){
                ingredientView.visibility = View.VISIBLE
                directionView.visibility = View.VISIBLE
                recycler.visibility = View.VISIBLE
            }

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

            animation.doOnEnd {
                if(!recipe!!.flipped){
                    ingredientView.visibility = View.GONE
                    directionView.visibility = View.GONE
                    recycler.visibility = View.GONE
                }
            }
        }

        private fun setupTab(){
            val linearLayoutManager = LinearLayoutManager(recycler.context, LinearLayoutManager.HORIZONTAL, false)
            recycler.layoutManager = linearLayoutManager

            recipe!!.let {
                if(it.ingredientsSelect){
                    ingredientsBar.alpha = 1.0f
                    directionBar.alpha = 0.0f
                    linearLayoutManager.initialPrefetchItemCount = it.ingredients.size
                    recycler.adapter = RecipeIngredientsAdaptor(this,  it.ingredients)
                }else{
                    directionBar.alpha = 1.0f
                    ingredientsBar.alpha = 0.0f
                    linearLayoutManager.initialPrefetchItemCount = it.instructions.size
                    recycler.adapter = RecipeDirectionAdaptor(this, it.instructions)
                }
            }
        }
    }
}