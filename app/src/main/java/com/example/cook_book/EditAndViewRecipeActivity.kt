package com.example.cook_book

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cook_book.recipe.viewDirectionAdaptor
import com.example.cook_book.recipe.viewIngredientAdaptor
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditAndViewRecipeActivity() : AppCompatActivity() {
    lateinit var  name: String
    lateinit var  description: String
    lateinit var  ingredients: ArrayList<String>
    lateinit var  instructions: ArrayList<String>
    lateinit var imageURIString : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_and_view_recipe)


        val titleTextView : TextView = findViewById(R.id.recipe_name)
        var descriptionView: TextView = findViewById(R.id.recipe_description)

        var imageView: ImageView = findViewById(R.id.recipe_image)


        val backButton: FloatingActionButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            var intent = Intent()
            intent.putExtra("deleted", false)
            setResult(2, intent)

            finish()
        }

        val deleteButton :FloatingActionButton = findViewById(R.id.deleteButton)
        deleteButton.setOnClickListener {
            var intent = Intent()
            intent.putExtra("name", name)
            intent.putExtra("deleted", true)
            setResult(2, intent)
            finish()
        }
//        val editButton: FloatingActionButton = findViewById(R.id.editButton)
//        backButton.setOnClickListener {
//        }

        val extras = intent.extras
        if (extras != null) {
            name = extras.getString("name").toString()
            titleTextView.setText(name)

            description = extras.getString("description").toString()
            descriptionView.setText(description)

            ingredients = extras.getStringArrayList("ingredients") as ArrayList<String>

            instructions = extras.getStringArrayList("instructions") as ArrayList<String>

            imageURIString = extras.getString("imageURI").toString()
            var imageURI: Uri = Uri.parse(imageURIString)
            imageView.setImageURI(imageURI)
        }

        var directionRecycler :RecyclerView = findViewById(R.id.directions_recycler)
        directionRecycler.layoutManager = LinearLayoutManager(this)
        var directionAdaptor = viewDirectionAdaptor(instructions)
        directionRecycler.adapter = directionAdaptor

        var ingredientRecycler :RecyclerView = findViewById(R.id.ingredient_recycler)
        ingredientRecycler.layoutManager = LinearLayoutManager(this)
        var ingredientsAdaptor = viewIngredientAdaptor(ingredients)
        ingredientRecycler.adapter = ingredientsAdaptor



    }
}