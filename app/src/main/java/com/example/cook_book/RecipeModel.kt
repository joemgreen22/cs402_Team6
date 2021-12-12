package com.example.cook_book

import android.graphics.Bitmap

public data class Recipe(
    var recipeName: String,
    var recipeDescription: String,
    var imageName: Int?,
    var image: Bitmap?,
    var ingredients: MutableList<String> = mutableListOf(),
    var instructions: MutableList<String> = mutableListOf(),
    var flipped: Boolean = false,
    var ingredientsSelect: Boolean = true
)

class RecipeModel : ArrayList<Recipe>() {

}