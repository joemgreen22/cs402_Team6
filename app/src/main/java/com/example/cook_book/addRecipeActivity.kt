package com.example.cook_book

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.ByteArrayOutputStream
import java.net.URI


class addRecipeActivity : AppCompatActivity() {
    private lateinit var nameLayout: TextInputLayout
    private lateinit var descriptionLayout: TextInputLayout
    private lateinit var ingredientsLayout: TextInputLayout
    private lateinit var instructionsLayout: TextInputLayout
    private lateinit var imageURI: Uri
    var ingredientsList = ArrayList<String>()
    var directionsList = ArrayList<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        nameLayout = findViewById(R.id.nameInput)
        descriptionLayout = findViewById(R.id.descriptionInput)
        ingredientsLayout = findViewById(R.id.ingredientsInput)
        instructionsLayout = findViewById(R.id.instructionsInput)
        val ingredientsTextView: TextView = findViewById(R.id.listIngredient)
        val directionsTextView: TextView = findViewById(R.id.listInstructions)
        var ingredientsInputText: TextInputEditText = findViewById(R.id.ingredientsInputText)
        var instructionsInputText: TextInputEditText = findViewById(R.id.instructionsInputText)
        var imageView: ImageView = findViewById(R.id.imageView)
        imageURI = R.drawable.default_recipe_image.toString().toUri()
        imageView.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.default_recipe_image))


        val backButton: FloatingActionButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val ingredientsButton: Button = findViewById(R.id.ingredientSubmit)
        ingredientsButton.setOnClickListener {
            var newIngredient = ingredientToString(ingredientsLayout)
            ingredientsList.add(newIngredient)
            var tmpIngredient = formatList(ingredientsList)
            ingredientsTextView.setText(tmpIngredient)
            ingredientsInputText.setText("")
        }

        val instructionButton: Button = findViewById(R.id.instructionsSubmit)
        instructionButton.setOnClickListener {
            var newInstructions = instructionToString(instructionsLayout)
            directionsList.add(newInstructions)
            var tmpDirection = formatList(directionsList)
            directionsTextView.setText(tmpDirection)
            instructionsInputText.setText("")
        }

        val submitButton: Button = findViewById(R.id.submitAll)
        submitButton.setOnClickListener {
            var intent = Intent()
            intent.putExtra("name", nameToString(nameLayout))
            intent.putExtra("description", descriptionToString(descriptionLayout))

            intent.putExtra("imageURI", imageURI.toString())
//            var imageURI: Uri = imageView.geturi
//            val drawable = imageView.drawable as BitmapDrawable
//            var bmap: Bitmap = drawable.getBitmap();
//            var bStream: ByteArrayOutputStream = ByteArrayOutputStream()
//            bmap.compress(Bitmap.CompressFormat.PNG, 0, bStream)
//            intent.putExtra("imageURI", bStream.toByteArray())

            intent.putStringArrayListExtra("ingredients", ingredientsList as ArrayList<String?>?)
            intent.putStringArrayListExtra("directions", directionsList as ArrayList<String?>?)
            var tmp1 = ingredientsList
            setResult(Activity.RESULT_OK, intent)
            finish()
        }



        val imageButton: Button = findViewById(R.id.pickImage)
        val loadImage = registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imageView.setImageURI(it)
                imageURI = it

            })

        imageButton.setOnClickListener() {
            loadImage.launch("image/*")
        }
    }


    fun nameToString(name: TextInputLayout): String {
        var sName = name.getEditText()?.getText().toString().trim()
        return sName
    }
    fun descriptionToString(description: TextInputLayout): String {
        var sDescription = description.getEditText()?.getText().toString().trim()
        return sDescription
    }
    fun ingredientToString(ingredients: TextInputLayout): String {
        var sIngredients = ingredients.getEditText()?.getText().toString().trim()
        return sIngredients
    }
    fun instructionToString(instruction: TextInputLayout): String {
        var sInstruction = instruction.getEditText()?.getText().toString().trim()
        return sInstruction
    }


    fun formatList(list: ArrayList<String>): String {
        var tmpString: String = ""
        var count =1
        for(i in list){
            if(i != null){
                tmpString = tmpString + count + ") " + i + "     "
                count ++
            }
        }
        return tmpString
    }


    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putStringArrayList("ingredientsListArray", ingredientsList)
        savedInstanceState.putStringArrayList("directionsListArray", directionsList)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var tempListOne = savedInstanceState.getStringArrayList("ingredientsListArray")
        ingredientsList = removeNUll(tempListOne)

        var tempListTwo = savedInstanceState.getStringArrayList("directionsListArray")
        directionsList = removeNUll(tempListTwo)
    }
}

private fun removeNUll(array: ArrayList<String>?): ArrayList<String> {
    var templist = ArrayList<String>()

    val size = array?.size
    if (size == null || array == null) {
        return templist
    }
    for (i in 0 until size) {
        templist.add(array[i])
    }
    return templist
}