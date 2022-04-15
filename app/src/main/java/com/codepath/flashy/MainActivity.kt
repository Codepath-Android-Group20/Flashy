package com.codepath.flashy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.codepath.flashy.models.Flashcard
import com.codepath.flashy.models.Flashcard.Companion.KEY_COLLECTION
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ParseObject.registerSubclass(Flashcard::class.java)

        queryFlashcards()

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
                            Log.i(TAG,"Front: " + flashcard.getFront() + " Back: "+ flashcard.getBack()
                            + " need to learn: " + flashcard.getLearn() + "from collection: " + flashcard.getCollectionTitle()?.objectId)
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