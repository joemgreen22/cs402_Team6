package com.example.cook_book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class LoadingActivity : AppCompatActivity() {

    var progressBarPercentage :Int = 0
    val myHandler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_activity)

        //TODO: Add code to load recipes here and remove button and use loading bar

        val myProgressBar: ProgressBar = findViewById(R.id.loadProgressBar)
        val completedText: TextView = findViewById(R.id.loadProgressBarTxView)

        Thread{
            while(progressBarPercentage < 100){
                Thread.sleep(50)
                myHandler.post {
                    myProgressBar.progress = progressBarPercentage + 1
                }
                progressBarPercentage++;
            }
            myHandler.post {
                completedText.visibility = View.VISIBLE
            }
            Thread.sleep(1000)
            Log.d("RECIPE", "Finished Loading.")
            Log.d("RECIPE", "Transitioning to RecipeListActivity")
            val intent = Intent(this, RecipeListActivity::class.java)
            startActivity(intent)

        }.start()
    }
}