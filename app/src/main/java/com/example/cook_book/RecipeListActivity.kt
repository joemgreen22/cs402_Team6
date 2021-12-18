package com.example.cook_book

import android.app.Activity
import android.content.Intent
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


class RecipeListActivity : AppCompatActivity() {
    private lateinit var kRecyclerView: RecyclerView
    private val loadingIntent = intent
    private  var recipeList : RecipeModel = RecipeModel()
    lateinit var  kadapter: RecipeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)


        recipeList.addAll(intent.getBundleExtra("myRecipeList")?.getSerializable("recipeList") as ArrayList<Recipe>)


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

                val bitmap : Bitmap
                var path : String = imageUri.toString()
                if(imageUri.toString().contains("/")){
                    bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                    path = imageUri.hashCode().toString()
                    File("/data/data/com.example.cook_book/files/", "$path.png").writeBitmap(bitmap, Bitmap.CompressFormat.PNG, 100)
                    path = "/data/data/com.example.cook_book/files/$path.png"
                }


                var newIngredients = value?.getStringArrayListExtra("ingredients")
                var mutableIngredients = toMutable(newIngredients)

                var newDirections = value?.getStringArrayListExtra("directions")
                var mutableDirections = toMutable(newDirections)

                recipeList.add(
                    Recipe(newName.toString(),
                        newDescription.toString(),
                        path,
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        kadapter.onActivityResult(requestCode, resultCode, data)
        kadapter.notifyDataSetChanged()
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

// https://stackoverflow.com/questions/11274715/save-bitmap-to-file-function
private fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }


}




