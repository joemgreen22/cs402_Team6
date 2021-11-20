package com.example.cook_book

import android.graphics.Bitmap

public data class Recipe(
    var recipeName: String,
    var recipeDescription: String,
    var image: Bitmap,
    var ingredients: MutableList<String> = mutableListOf(),
    var instructions: MutableList<String> = mutableListOf(),
    var flipped: Boolean = false
)

class RecipeModel : ArrayList<Recipe>() {

}