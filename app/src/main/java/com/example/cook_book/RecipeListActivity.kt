package com.example.cook_book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeListActivity : AppCompatActivity() {
    private lateinit var kRecyclerView: RecyclerView
    val recipeList = recipeModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        kRecyclerView = findViewById(R.id.recipe_recycler_view) as RecyclerView
        kRecyclerView.layoutManager = LinearLayoutManager(this)

        // Adapter here
        val kadapter: recipeAdapter = recipeAdapter(this, recipeList)

        kRecyclerView.setAdapter(kadapter)


        // add
        val addButton: Button = findViewById(R.id.addButton)
        var acount: Int = 0
        addButton.setOnClickListener {
            acount += 1
            recipeList.add(recipeCard("aNewRecipe", false))
            kadapter.notifyDataSetChanged()
        }


        // Remove
        val removeButton: Button = findViewById(R.id.removeButton)
        removeButton.setOnClickListener {
            for (i in recipeList.size downTo 1) {
                if (recipeList[i - 1].selected) {
                    recipeList.removeAt(i - 1)
                }
            }
            kadapter.notifyDataSetChanged()

        }
    }
}