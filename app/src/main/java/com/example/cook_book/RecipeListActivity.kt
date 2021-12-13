package com.example.cook_book

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import android.provider.MediaStore
import org.json.JSONObject
import org.json.JSONArray
import java.io.File
import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.net.toUri
import java.io.InputStream


class RecipeListActivity : AppCompatActivity() {
    private lateinit var kRecyclerView: RecyclerView
    private var recipeList = RecipeModel()
    lateinit var  kadapter: RecipeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        var ingredients = ArrayList<String>()
        var directions = ArrayList<String>()

        var jsonString: String
        var jArray: JSONArray? = null

        if(File("/data/data/com.example.cook_book/files/recipeData.json").exists()){
            jsonString = File("/data/data/com.example.cook_book/files/recipeData.json").readText(Charsets.UTF_8)
            jArray = JSONArray(jsonString)
        }

        if (jArray != null && jArray.length() > 0) {

                var ingredientsArray : JSONArray
                var instructionsArray : JSONArray

                for(i in 0 until jArray.length()){
                    var jObject : JSONObject = jArray.getJSONObject(i)

                    ingredientsArray = jObject.get("ingredients") as JSONArray
                    instructionsArray = jObject.get("instructions") as JSONArray

                    ingredients = ArrayList<String>()
                    for(i in 0 until ingredientsArray.length()){
                        ingredients.add(ingredientsArray.getString(i))
                    }
                    directions = ArrayList<String>()
                    for(i in 0 until instructionsArray.length()){
                        directions.add(instructionsArray.getString(i))
                    }

                    var bitmap : Bitmap
                    if(!jObject.getString("ImageName").contains("/")){
                        bitmap = BitmapFactory.decodeResource(resources, jObject.getString("ImageName").toInt())
                    } else {
                        val imageUri: Uri = Uri.parse(jObject.getString("ImageName"))
                        bitmap = BitmapFactory.decodeResource(resources, R.drawable.def_chicken_img_1)
                    }

                    recipeList.add(
                        Recipe(jObject.getString("Name"),
                            jObject.getString("Description"),
                            jObject.getString("ImageName"),
                            bitmap,
                            ingredients,
                            directions
                        )
                    )
                }
            } else {
                ingredients.add("8 Chicken Drumsticks")
                ingredients.add("Olive Oil")
                ingredients.add("Kosher salt and black pepper")
                ingredients.add("Spices")

                directions.add("Your first step is to preheat your oven to 400 degrees F. Then line a rimmed baking sheet with parchment paper.")
                directions.add("Now, mix the oil with the spices into a paste. Arrange the drumsticks in a single layer on the baking sheet and coat them all over with the paste. You can use a pastry brush or simply your hands.")
                directions.add("The last step is to bake the chicken pieces until their internal temperature reaches 165 degrees F. This should take about 40 minutes for medium drumsticks.")

                recipeList.add(
                    Recipe("Chicken Drumsticks",
                        "After a quick marinade, these drumsticks bake in the oven in no time and stay extremely tender and juicy. The high heat helps the skin get nice and crispy for a perfectly baked drumstick that only take about 20 minutes to bake!",
                        R.drawable.def_chicken_img_1.toString(),
                        BitmapFactory.decodeResource(resources, R.drawable.def_chicken_img_1),
                        ingredients,
                        directions)
                )

                ingredients = ArrayList<String>()
                directions = ArrayList<String>()

                ingredients.add("2 boneless skinless chicken breast halves (7 to 8 ounces each)")
                ingredients.add("1/2 cup canola oil")
                ingredients.add("1/4 cup drained jarred capers, patted dry")
                ingredients.add("2/3 cup all-purpose flour")
                ingredients.add("1/2 cup freshly grated Parmesan")
                ingredients.add("Kosher salt and freshly ground black pepper")
                ingredients.add("1 lemon, zested and halved, plus 1 lemon sliced into rounds")
                ingredients.add("2 large eggs")
                ingredients.add("3 tablespoons heavy cream")
                ingredients.add("4 tablespoons unsalted butter")
                ingredients.add("1/2 cup dry white wine")
                ingredients.add("1/2 cup fresh parsley leaves with tender stems, finely chopped")
                ingredients.add("1 1/2 cups chicken stock")
                ingredients.add("Cooked pasta, rice, and/or vegetables, for serving, optional")

                directions.add("Pat the chicken breasts dry with a paper towel. Press your palm down on a chicken breast and slice the chicken in half with a sharp knife parallel to the cutting board. Repeat with the second chicken breast to make a total of 4 cutlets.")
                directions.add("Heat the oil in a large heavy-bottomed skillet over medium-high heat until shimmering. Add the capers and fry until golden brown and crispy, 1 to 2 minutes. Remove from the skillet using a slotted spoon and drain on a paper towel-lined plate. Reserve the oil in the skillet and remove from the heat.")
                directions.add("Set up a dredging station with 2 shallow bowls or pie plates. Combine the flour with 1/4 cup of the Parmesan, 1 tablespoon kosher salt and the lemon zest in 1 bowl; stir with a fork to combine. Whisk the eggs with 2 tablespoons of the cream in the other bowl.")
                directions.add("Heat the reserved oil in the skillet over medium heat.")
                directions.add("Working in an assembly line, dredge 1 chicken cutlet in the seasoned flour and turn to coat both sides; tap off any excess. Dip the chicken cutlet into the beaten eggs and coat both sides, letting any excess drip off. Carefully lower the cutlet into the skillet. Repeat with the remaining chicken cutlets, frying them in a single layer, until the chicken is opaque and golden brown, 4 to 5 minutes per side. Transfer the chicken to a paper towel-lined baking sheet and set aside.")
                directions.add("Carefully pour off the oil and discard. Wipe out the skillet with a paper towel. Add the butter and cook over medium heat, swirling the skillet occasionally, until the butter bubbles and begins to brown, 1 to 2 minutes. Add the wine and the juice of 1/2 the zested lemon, increase the heat to medium-high and continue to cook, stirring occasionally, until the liquid is reduced and thickened, 5 to 6 minutes.")
                directions.add("Add the chicken stock to the skillet and continue to cook over medium-high heat, stirring occasionally, until the sauce is reduced and thickened, about 5 minutes.")
                directions.add("Reduce the heat to medium-low and whisk in the remaining 1 tablespoon cream until emulsified.")
                directions.add("Taste and adjust the seasoning, if necessary. Return the chicken to the skillet, add the lemon slices and increase the heat to medium. Cook, spooning the sauce over the chicken, until it's warmed through and the lemons have started to soften, 3 to 4 minutes.")
                directions.add("Divide the chicken among plates, top with the lemon slices and spoon the sauce over the top.")
                directions.add("Sprinkle with the remaining 1/4 cup of Parmesan, the parsley and fried capers and enjoy.")

                recipeList.add(
                    Recipe("Roasted Capers & Buttered Chicken",
                        "This Italian-American classic dish gets a nutty addition of browned butter and a briny crunch from crispy capers. It's sure to be a hit.",
                        R.drawable.def_chicken_img_2.toString(),
                        BitmapFactory.decodeResource(resources, R.drawable.def_chicken_img_2),
                        ingredients,
                        directions)
                )

                ingredients = ArrayList<String>()
                directions = ArrayList<String>()

                ingredients.add("33 Regular Oreos")
                ingredients.add("1/2 Stick of Butter")
                ingredients.add("1 Cup Cream Cheese")
                ingredients.add("3/2 Cup sugar")
                ingredients.add("3/4 Cup Sour Cream")
                ingredients.add("1 Tbsp Vanilla Extract")
                ingredients.add("3 Eggs")

                directions.add("Preheat oven to 350. Line a springform pan with heavy duty foil (so water won’t leak in from water bath) and grease.")
                directions.add("Make the crust: Mix 20 crushed Oreos with melted butter then press into bottom of pan. Bake 9 – 10 minutes.")
                directions.add("Make the filling: mix cream cheese and sugar then blend in sour cream and vanilla, followed by eggs.")
                directions.add("Fold in 13 crushed Oreos then pour filling over crust layer.")
                directions.add("Place in roasting pan and the pour hot water around it.")
                directions.add("Bake 50 – 60 minutes then turn off oven and let rest 1 hour.")
                directions.add("Cool to room temp, then chill 4 hours.")
                directions.add("Decorate as desired.")

                recipeList.add(
                    Recipe("Oreo Cheesecake",
                        "The best Oreo Cheesecake you've ever tasted. French vanilla Ice Cream topped with crushed original ores.",
                        R.drawable.def_dessert_img_1.toString(),
                        BitmapFactory.decodeResource(resources, R.drawable.def_dessert_img_1),
                        ingredients,
                        directions)
                )

                ingredients = ArrayList<String>()
                directions = ArrayList<String>()

                ingredients.add("4 large egg yolks")
                ingredients.add("1/2 cup or 100 grams - granulated sugar, divided")
                ingredients.add("3/4 cup heavy cream")
                ingredients.add("1 cup or 227 grams - mascarpone (8 ounces)")
                ingredients.add("1 3/4 cups good espresso or very strong coffee")
                ingredients.add("2 tablespoons rum or cognac")
                ingredients.add("2 tablespoons unsweetened cocoa powder")
                ingredients.add("About 24 ladyfingers (from one 7-ounce/200-gram package)")
                ingredients.add("1 to 2 ounces bittersweet chocolate, for shaving (optional)")

                directions.add("Using an electric mixer in a medium bowl, whip together egg yolks and 1/4 cup/50 grams sugar until very pale yellow and about tripled in volume. A slight ribbon should fall from the beaters (or whisk attachment) when lifted from the bowl. Transfer mixture to a large bowl, wiping out the medium bowl used to whip the yolks and set aside.")
                directions.add("In the medium bowl, whip cream and remaining 1/4 cup/50 grams sugar until it creates soft-medium peaks. Add mascarpone and continue to whip until it creates a soft, spreadable mixture with medium peaks. Gently fold the mascarpone mixture into the sweetened egg yolks until combined.")
                directions.add("Combine espresso and rum in a shallow bowl and set aside.")
                directions.add("Using a sifter, dust the bottom of a 2-quart baking dish (an 8x8-inch dish, or a 9-inch round cake pan would also work here) with 1 tablespoon cocoa powder.")
                directions.add("Working one at a time, quickly dip each ladyfinger into the espresso mixture -- they are quite porous and will fall apart if left in the liquid too long -- and place them rounded side up at the bottom of the baking dish. Repeat, using half the ladyfingers, until you’ve got an even layer, breaking the ladyfingers in half as needed to fill in any obvious gaps (a little space in between is O.K.). Spread half the mascarpone mixture onto the ladyfingers in one even layer. Repeat with remaining espresso-dipped ladyfingers and mascarpone mixture.")
                directions.add("Dust top layer with remaining tablespoon of cocoa powder. Top with shaved or finely grated chocolate, if desired.")
                directions.add("Cover with plastic wrap and let chill in the refrigerator for at least 4 hours (if you can wait 24 hours, all the better) before slicing or scooping to serve.")

                recipeList.add(
                    Recipe("Tiramisu",
                        "A creamy dessert of espresso-soaked ladyfingers surrounded by lightly sweetened whipped cream and a rich mascarpone, tiramisù relies heavily on the quality of its ingredients. If you don’t have a barista setup at home, pick up the espresso at a local coffee shop, or use strongly brewed coffee.",
                        R.drawable.def_dessert_img_2.toString(),
                        BitmapFactory.decodeResource(resources, R.drawable.def_dessert_img_2),
                        ingredients,
                        directions)
                )

                ingredients = ArrayList<String>()
                directions = ArrayList<String>()

                ingredients.add("Strawberries")
                ingredients.add("Pineapple")
                ingredients.add("Blueberries")
                ingredients.add("Red grapes")
                ingredients.add("Kiwi")
                ingredients.add("Mandarin oranges")
                ingredients.add("Bananas")
                ingredients.add("Honey")
                ingredients.add("Limes")

                directions.add("Prepare fruit: chop fruits (except blueberries) and add to a large mixing bowl.")
                directions.add("Make dressing: in a small mixing bowl, whisk together they honey, lime zest and lime juice.")
                directions.add("Toss fruit with dressing: pour dressing over fruit just before serving and toss to evenly coat.")

                recipeList.add(
                    Recipe("Fruit Salad With Honey Dressing",
                        "The BEST Fruit Salad with a sweet and bright honey lime dressing! It’s an incredibly refreshing, must have side dish that’s made with beautiful blend of delicious fruits and a simple dressing to compliment it. This is always sure to be a crowd favorite!",
                        R.drawable.def_salad_img_1.toString(),
                        BitmapFactory.decodeResource(resources, R.drawable.def_salad_img_1),
                        ingredients,
                        directions)
                )

                ingredients = ArrayList<String>()
                directions = ArrayList<String>()

                ingredients.add("1 large or 2 small heads of romaine lettuce")
                ingredients.add("Parmesan cheese, shredded or shaved")
                ingredients.add("Crisp croutons – homemade can be made several days ahead. The recipe below makes enough for 2 salads.")
                ingredients.add("Caesar salad dressing – homemade is best and here is our favorite store-bought dressing in a pinch")

                directions.add("Cut baguette in half lengthwise and slice into 1/4″ thick pieces and place on baking sheet.")
                directions.add("Combine 3 Tbsp extra virgin olive oil with minced garlic. toss breads with garlic infused oil and 2 Tbsp parmesan.")
                directions.add("Spread Evenly and Bake to desired crispness.")
                directions.add("Whisk together minced garlic, dijon, Worcestershire, lemon juice and red wine vinegar.")
                directions.add("Whisking while adding oil emulsifies the dressing for a smooth and creamy (not oily) consistency.")
                directions.add("Season with 1/2 tsp salt and 1/8 tsp black pepper, or to taste.")
                directions.add("In a large mixing bowl, combine all of your ingredients and toss gently to coat the lettuce in caesar dressing. This recipe makes enough croutons for two full salads so you’ll have them ready to go for round 2!")

                recipeList.add(
                    Recipe("Caesar Salad",
                        "Classic Caesar Salad with crisp homemade croutons and a light caesar dressing – for when you want to impress your dinner guests.",
                        R.drawable.def_salad_img_2.toString(),
                        BitmapFactory.decodeResource(resources, R.drawable.def_salad_img_2),
                        ingredients,
                        directions)
                )

                ingredients = ArrayList<String>()
                directions = ArrayList<String>()

                ingredients.add("2 eggplants")
                ingredients.add("6 roma tomatoes")
                ingredients.add("2 yellow squashes")
                ingredients.add("2 zucchinis")
                ingredients.add("6 tablespoons olive oil")
                ingredients.add("1 onion, diced")
                ingredients.add("4 cloves garlic, minced")
                ingredients.add("1 red bell pepper, diced")
                ingredients.add("1 yellow bell pepper, diced")
                ingredients.add("salt, to taste")
                ingredients.add("pepper, to taste")
                ingredients.add("28 oz can of crushed tomatoes")
                ingredients.add("4 tablespoons chopped fresh basil, from 16-20 leaves")
                ingredients.add("1 teaspoon garlic, minced")
                ingredients.add("2 tablespoons Chopped fresh parsley")
                ingredients.add("2 teaspoons fresh thyme")

                directions.add("Preheat the oven for 375˚F (190˚C).")
                directions.add("Slice the eggplant, tomatoes, squash, and zucchini into approximately 1/16-inch (1-mm) rounds, then set aside.")
                directions.add("Make the sauce: Heat the olive oil in a 12-inch (30-cm) oven-safe pan over medium-high heat. Sauté the onion, garlic, and bell peppers until soft, about 10 minutes. Season with salt and pepper, then add the crushed tomatoes. Stir until the ingredients are fully incorporated. Remove from heat, then add the basil. Stir once more, then smooth the surface of the sauce with a spatula.")
                directions.add("Arrange the sliced veggies in alternating patterns, (for example, eggplant, tomato, squash, zucchini) on top of the sauce from the outer edge to the middle of the pan. Season with salt and pepper.")
                directions.add("Make the herb seasoning: In a small bowl, mix together the basil, garlic, parsley, thyme, salt, pepper, and olive oil. Spoon the herb seasoning over the vegetables.")
                directions.add("Cover the pan with foil and bake for 40 minutes. Uncover, then bake for another 20 minutes, until the vegetables are softened.")
                directions.add("Serve while hot as a main dish or side. The ratatouille is also excellent the next day--cover with foil and reheat in a 350˚F (180˚C) oven for 15 minutes, or simply microwave to desired temperature.")

                recipeList.add(
                    Recipe("Ratatouille",
                        "Ratatouille is a classic end-of-summer French stew that’s fun to say (rat-tuh-TOO-ee) and fun to make. It’s packed with fresh produce: tomatoes, eggplant, zucchini and yellow squash, and bell pepper.",
                        R.drawable.def_soup_img_1.toString(),
                        BitmapFactory.decodeResource(resources, R.drawable.def_soup_img_1),
                        ingredients,
                        directions)
                )

                ingredients = ArrayList<String>()
                directions = ArrayList<String>()

                ingredients.add("2 tablespoons olive oil")
                ingredients.add("1 medium onion, diced")
                ingredients.add("1 red bell pepper, seeded and diced")
                ingredients.add("4 garlic cloves, finely chopped")
                ingredients.add("2 teaspoon paprika")
                ingredients.add("1 teaspoon cumin")
                ingredients.add("1/4 teaspoon chili powder")
                ingredients.add("1 28-ounce can whole peeled tomatoes")
                ingredients.add("6 large eggs")
                ingredients.add("salt and pepper, to taste")
                ingredients.add("1 small bunch fresh cilantro, chopped")
                ingredients.add("1 small bunch fresh parsley, chopped")

                directions.add("Heat olive oil in a large sauté pan on medium heat. Add the chopped bell pepper and onion and cook for 5 minutes or until the onion becomes translucent.")
                directions.add("Add garlic and spices and cook an additional minute.")
                directions.add("Pour the can of tomatoes and juice into the pan and break down the tomatoes using a large spoon. Season with salt and pepper and bring the sauce to a simmer.")
                directions.add("Use your large spoon to make small wells in the sauce and crack the eggs into each well. Cover the pan and cook for 5-8 minutes, or until the eggs are done to your liking.")
                directions.add("Garnish with chopped cilantro and parsley.")

                recipeList.add(
                    Recipe("Shakshuka",
                        "Shakshuka is an easy, healthy breakfast (or any time of day) recipe in Israel and other parts of the Middle East and North Africa. It’s a simple combination of simmering tomatoes, onions, garlic, spices and gently poached eggs. It’s nourishing, filling and one recipe I guarantee you’ll make time and again.",
                        R.drawable.def_soup_img_2.toString(),
                        BitmapFactory.decodeResource(resources, R.drawable.def_soup_img_2),
                        ingredients,
                        directions)
                )
            }

        kRecyclerView = findViewById<RecyclerView>(R.id.recipe_recycler_view)
        kRecyclerView.layoutManager = LinearLayoutManager(this)

        kadapter = RecipeAdapter(this, recipeList)
        kRecyclerView.adapter = kadapter


        val intent = Intent(this, addRecipeActivity::class.java)
        val addButton: FloatingActionButton = findViewById(R.id.addButton)

        val activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val value = result.data
                var newName = value?.getStringExtra("name")
                var newDescription = value?.getStringExtra("description")

                val imageUri: Uri = Uri.parse(value?.getStringExtra("imageURI"))
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)


                var newIngredients = value?.getStringArrayListExtra("ingredients")
                var mutableIngredients = toMutable(newIngredients)

                var newDirections = value?.getStringArrayListExtra("directions")
                var mutableDirections = toMutable(newDirections)

                recipeList.add(
                    Recipe(newName.toString(),
                        newDescription.toString(),
                        imageUri.encodedPath.toString(),
                        bitmap,
                        mutableIngredients,
                        mutableDirections)
                )
                kadapter.notifyDataSetChanged()
            }
        }

        addButton.setOnClickListener {
            activityResultLauncher.launch(intent)
        }

    }

    override fun onPause() {
        super.onPause()

        var jsonArray = JSONArray()
        var jsonObject = JSONObject()
        val fos = openFileOutput("recipeData.json", Context.MODE_PRIVATE)

        var output = ""
        for(recipe in recipeList){
            jsonObject = JSONObject()
            jsonObject.put("Name", recipe.recipeName);
            jsonObject.put("Description", recipe.recipeDescription);
            jsonObject.put("ImageName", recipe.imageName);
            jsonObject.put("Image", recipe.image);

            var array = JSONArray()
            for(ingredient in recipe.ingredients){
                array.put(ingredient)
            }
            jsonObject.put("ingredients", array)

            array = JSONArray()
            for(instruction in recipe.instructions){
                array.put(instruction)
            }

            jsonObject.put("instructions", array)
            jsonArray.put(jsonObject)
        }
        fos.write(jsonArray.toString().toByteArray())
    }
}

private fun toMutable(array: ArrayList<String>?): MutableList<String> {
    var tempMutable: MutableList<String> = mutableListOf()

    val size = array?.size
    if (size == null || array == null) {
        tempMutable.add("None")
        return tempMutable
    }
    for (i in 0 until size) {
        tempMutable.add(array[i])
    }
    return tempMutable
}
