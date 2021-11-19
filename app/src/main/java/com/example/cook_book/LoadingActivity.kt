package com.example.cook_book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_activity)

        //TODO: Add code to load recipes here and remove button and use loading bar
        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            Log.d("RECIPE", "Finished Loading.")
            Log.d("RECIPE", "Transitioning to RecipeListActivity")
            val intent = Intent(this, RecipeListActivity::class.java)
            startActivity(intent)
        }
    }
}