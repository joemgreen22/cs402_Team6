package com.example.cook_book.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cook_book.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditAndViewRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_and_view_recipe)

        val backButton: FloatingActionButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}