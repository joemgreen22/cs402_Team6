package com.example.cook_book

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeListActivity : AppCompatActivity() {
    private lateinit var kRecyclerView: RecyclerView
    private var recipeList = RecipeModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        recipeList.add(Recipe("Chicken Drumsticks",
            "After a quick marinade, these drumsticks bake in the oven in no time and stay extremely tender and juicy. The high heat helps the skin get nice and crispy for a perfectly baked drumstick that only take about 20 minutes to bake!",
            BitmapFactory.decodeResource(resources, R.drawable.def_chicken_img_1)))
        recipeList.add(Recipe("Roasted Capers & Buttered Chicken",
            "This Italian-American classic dish gets a nutty addition of browned butter and a briny crunch from crispy capers. It's sure to be a hit.",
            BitmapFactory.decodeResource(resources, R.drawable.def_chicken_img_2)))
        recipeList.add(Recipe("Oreo Cheesecake",
            "The best Oreo Cheesecake you've ever tasted. French vanilla Ice Cream topped with crushed original ores.",
            BitmapFactory.decodeResource(resources, R.drawable.def_dessert_img_1)))
        recipeList.add(Recipe("Tiramisu",
            "A creamy dessert of espresso-soaked ladyfingers surrounded by lightly sweetened whipped cream and a rich mascarpone, tiramisù relies heavily on the quality of its ingredients. If you don’t have a barista setup at home, pick up the espresso at a local coffee shop, or use strongly brewed coffee.",
            BitmapFactory.decodeResource(resources, R.drawable.def_dessert_img_2)))
        recipeList.add(Recipe("Fruit Salad With Honey Dressing",
            "The BEST Fruit Salad with a sweet and bright honey lime dressing! It’s an incredibly refreshing, must have side dish that’s made with beautiful blend of delicious fruits and a simple dressing to compliment it. This is always sure to be a crowd favorite!",
            BitmapFactory.decodeResource(resources, R.drawable.def_salad_img_1)))
        recipeList.add(Recipe("Caesar Salad",
            "Classic Caesar Salad with crisp homemade croutons and a light caesar dressing – for when you want to impress your dinner guests.",
            BitmapFactory.decodeResource(resources, R.drawable.def_salad_img_2)))
        recipeList.add(Recipe("Ratatouille",
            "Ratatouille is a classic end-of-summer French stew that’s fun to say (rat-tuh-TOO-ee) and fun to make. It’s packed with fresh produce: tomatoes, eggplant, zucchini and yellow squash, and bell pepper.",
            BitmapFactory.decodeResource(resources, R.drawable.def_soup_img_1)))
        recipeList.add(Recipe("Shakshuka",
            "Shakshuka is an easy, healthy breakfast (or any time of day) recipe in Israel and other parts of the Middle East and North Africa. It’s a simple combination of simmering tomatoes, onions, garlic, spices and gently poached eggs. It’s nourishing, filling and one recipe I guarantee you’ll make time and again.",
            BitmapFactory.decodeResource(resources, R.drawable.def_soup_img_1)))

        kRecyclerView = findViewById<RecyclerView>(R.id.recipe_recycler_view)
        kRecyclerView.layoutManager = LinearLayoutManager(this)

        val kadapter = RecipeAdapter(recipeList)
        kRecyclerView.adapter = kadapter
    }
}