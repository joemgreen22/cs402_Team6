package com.example.cook_book

import android.graphics.Bitmap
import java.io.Serializable

public data class Recipe (
    var recipeName: String,
    var recipeDescription: String,
    var imageName: String,
    var ingredients: MutableList<String> = mutableListOf(),
    var instructions: MutableList<String> = mutableListOf(),
    var flipped: Boolean = false,
    var ingredientsSelect: Boolean = true
) : Serializable

class RecipeModel : ArrayList<Recipe>(), Serializable{

}