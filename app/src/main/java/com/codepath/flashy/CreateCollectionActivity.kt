package com.codepath.flashy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import com.codepath.flashy.models.Collection
import com.parse.ParseUser

class CreateCollectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_collection)

        findViewById<Button>(R.id.btn_create).setOnClickListener {
            // get the subject title that the user input
            val author = ParseUser.getCurrentUser()
            val title = findViewById<EditText>(R.id.et_title).text.toString()
            val description = findViewById<EditText>(R.id.et_description).text.toString()
            val rating = findViewById<RatingBar>(R.id.rbVoteAvarage).rating.toDouble()

            createCollection(author, title, description, rating)
        }
    }

    private fun createCollection(author: ParseUser, title: String, description: String, rating: Double) {
        val collection = Collection()
        collection.setAuthor(author)
        collection.setTitle(title)
        collection.setDescription(description)
        collection.setRating(rating)
        collection.saveInBackground { e ->
            if (e != null) {
                // somethiong has went wrong
                Log.e(TAG, "Error while saving post")
                e.printStackTrace()
                // show toast to tell user something went wrong with saving post

            } else {
                Log.i(TAG, "Succeessfully save post")
                // TODO: resetting the EditText field to be empty
                // TODO: reset the ImageView to empty
            }
        }
    }

    companion object {
        const val TAG = "CreateCollection"
    }
}