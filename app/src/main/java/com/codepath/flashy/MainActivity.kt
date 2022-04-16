package com.codepath.flashy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.codepath.flashy.models.Flashcard
import com.codepath.flashy.models.Collection
import com.codepath.flashy.models.Flashcard.Companion.KEY_COLLECTION
import com.codepath.flashy.models.Question
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ParseObject.registerSubclass(Flashcard::class.java)
        queryFlashcards()

        ParseObject.registerSubclass(Collection::class.java)
        queryCollections()

        ParseObject.registerSubclass(Question::class.java)
        queryQuestion()

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
                item ->

            var fragmentToShow: Fragment? = null
            when (item.itemId){

                R.id.action_Logout ->{
                    ParseUser.logOut()
                    val currentUser = ParseUser.getCurrentUser() // this will now be null
                    val intent = Intent(this ,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }


            }
            if (fragmentToShow != null){

            }
            true
        }


    }

    fun queryFlashcards(){
        val query: ParseQuery<Flashcard> = ParseQuery.getQuery(Flashcard::class.java)
        query.include(Flashcard.KEY_COLLECTION)
        query.findInBackground(object: FindCallback<Flashcard>{
            override fun done(flashcards: MutableList<Flashcard>?, e: ParseException?) {
                if (e!=null){
                    Log.e(TAG,"Error fetching flashcards")
                }else {
                    if (flashcards != null){
                        for (flashcard in flashcards){
                            Log.i(TAG,"Front: " + flashcard.getFront() + " , Back: "+ flashcard.getBack()
                            + " , Need To Learn: " + flashcard.getLearn() + ", From Collection (need to be fixed into a collection title): "
                            + flashcard.getCollection().getTitle())
                        }
                    }
                }
                }
        })
    }

    fun queryCollections(){
        val query: ParseQuery<Collection> = ParseQuery.getQuery(Collection::class.java)
        query.include(Collection.KEY_AUTHOR)
        query.findInBackground(object: FindCallback<Collection>{
            override fun done(collections: MutableList<Collection>?, e: ParseException?) {
                if (e!=null){
                    Log.e(TAG,"Error fetching collections")
                }else {
                    if (collections != null){
                        for (collection in collections){
                            Log.i(TAG,"Collection ID: " + collection.objectId + " , Author: "
                            + collection.getAuthor()?.username + " , Title: " +  collection.getTitle()
                            + " , Description: " + collection.getDescription() + " , Rating: " + collection.getRating()
                            + " , Number of views: " + collection.getTimesViewed() + " , Times Downloaded: "
                            + collection.getTimesDownloaded() + " ,CreatedAt: " + collection.createdAt)
                        }
                    }
                }
            }
        })
    }

    fun queryQuestion() {
        val query: ParseQuery<Question> = ParseQuery.getQuery(Question::class.java)
        query.include(Question.FLASHCARD)
//        // return posts in descending order: newer posts will appear first
//        query.addDescendingOrder("createdAt")
//        // limit to at most 20 posts
//        query.limit = 20;
        // find all post objects
        query.findInBackground(object: FindCallback<Question> {
            override fun done(questions: MutableList<Question>?, e: ParseException?) {
                if (e != null) {
                    // something has went wrong
                    Log.e(TAG, "error fetching posts")
                } else {
                    if (questions != null) {
                        for (question in questions) {
                            Log.i(TAG, "Question: " + question.getTerm() + " Term: " + question.getFlashCard().getFront())
                        }

                    }
                }
            }
        })
    }

    companion object{
        const val TAG = "MainActivity"
    }
}