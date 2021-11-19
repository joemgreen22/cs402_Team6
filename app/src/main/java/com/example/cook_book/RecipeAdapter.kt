package com.example.cook_book

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wajahatkarim3.easyflipview.EasyFlipView

public class RecipeAdapter (var recipes: RecipeModel) : RecyclerView.Adapter<RecipeAdapter.RecipeCard>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecipeAdapter.RecipeCard {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_vard_view, parent, false)
        return RecipeCard(view)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(card: RecipeCard, position: Int) {
        val aRecipe = recipes[position]
        card.apply {
            setTitle(aRecipe.recipeName)
            setDescription(aRecipe.recipeDescription)
            setRecipeImage(aRecipe.image)
//            setIngredients(aRecipe.ingredients)
//            setInstructions(aRecipe.instructions)
        }
    }

    inner class RecipeCard(view: View) : RecyclerView.ViewHolder(view) {
        private var easyFlipCard: EasyFlipView = view.findViewById(R.id.easy_flip_view)
        private var title: TextView = easyFlipCard.findViewById(R.id.recipe_name)
        private var description: TextView = easyFlipCard.findViewById(R.id.recipe_description)
        private var image: ImageView = easyFlipCard.findViewById(R.id.recipe_image)

        init {
            view.setOnClickListener {
              easyFlipCard.flipTheView()
            }
        }

        fun setTitle(tle: String){
            title.text = tle
        }

        fun setDescription(desc: String){
            description.text = desc
        }

        fun setRecipeImage(img: Bitmap){
            image.setImageBitmap(img)
        }

        fun setInstructions(instructions: List<String>){
            //TODO
        }

        fun setIngredients(ingredients: List<String>){
            //TODO
        }
    }
}