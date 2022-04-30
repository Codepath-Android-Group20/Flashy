package com.codepath.flashy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.models.Collection
import com.codepath.flashy.models.Flashcard
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery


class OtherUsersCollectionActivity : AppCompatActivity() {
    lateinit var collectionID: String
    lateinit var rvFlashcard: RecyclerView
    lateinit var flashcardAdapter:OtherUsersFlashcardAdapter
    lateinit var collection: Collection
    val displayedFlashcards:ArrayList<Flashcard> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_users_collection)
        rvFlashcard= findViewById(R.id.rvFlashcard)
        flashcardAdapter=OtherUsersFlashcardAdapter(this, displayedFlashcards)
        collectionID = intent.getStringExtra(COLLECTION_ID_EXTRA).toString()
        val collectionTitle = intent.getStringExtra(COLLECTION_TITLE_EXTRA).toString()
        findViewById<EditText>(R.id.etCollectionName).setText(collectionTitle)
//        if (collection!=null)
        Log.i(TAG, "collection is $collectionID")
//        else
//            Log.i(TAG, "No collection title")
        rvFlashcard.adapter = flashcardAdapter
        rvFlashcard.layoutManager = LinearLayoutManager(this)
        queryFlascard()

        findViewById<Button>(R.id.btn_quiz).setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putParcelableArrayListExtra("Collection", displayedFlashcards)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnDone).setOnClickListener{
            finish()
        }



//        findViewById<Button>(R.id.btnDone).setOnClickListener {
//            // initiating a rating bar
//            ratingBar = findViewById(R.id.rbVoteAvarage)
//            // get a rating number from the rating bar
//            var ratingNum: Number = ratingBar.rating
//
//            updateRatingCollection(collectionID, ratingNum)
//
//            finish()
//        }
    }

    private fun createFlashCard(front: String, back: String, needToLearn: Boolean, collectionID: String) {
        val flashcard = Flashcard()
        flashcard.setFront(front)
        flashcard.setBack(back)
        flashcard.setLearn(needToLearn)
        flashcard.setCollectionID(collectionID)
        flashcard.saveInBackground { e ->
            if (e != null) {
                // somethiong has went wrong
                Log.e(TAG, "Error while saving flashcard")
                e.printStackTrace()
                // show toast to tell user something went wrong with saving post
            } else {
                Log.i(TAG, "Succeessfully save flashcard")
                // TODO: resetting the EditText field to be empty
                // TODO: reset the ImageView to empty
            }
        }
    }

    private fun queryFlascard() {
        val query: ParseQuery<Flashcard> = ParseQuery.getQuery(Flashcard::class.java)
        query.include(Flashcard.KEY_COLLECTION_ID)
        query.whereEqualTo(Flashcard.KEY_COLLECTION_ID, collectionID)
        query.findInBackground(object: FindCallback<Flashcard> {
            override fun done(flashcards: MutableList<Flashcard>?, e: ParseException?) {
                if (e!=null){
                    Log.e(MainActivity.TAG,"Error fetching flashcards")
                }else {
                    if (flashcards != null){
                        displayedFlashcards.addAll(flashcards)
                        flashcardAdapter.notifyDataSetChanged()
//                        for (flashcard in flashcards){
//                            Log.i(
//                                MainActivity.TAG,"Front: " + flashcard.getFront() + " , Back: "+ flashcard.getBack()
//                                    + " , Need To Learn: " + flashcard.getLearn() + ", From Collection (need to be fixed into a collection title): "
//                                    + flashcard.getCollection().getTitle())
//                        }
                    }
                }
            }
        })
    }

    private fun updateRatingCollection(collectionID: String, ratingNum: Number) {
        val query = ParseQuery.getQuery<ParseObject>("Collection")
        // Retrieve the object by id
        // Retrieve the object by id
        query.getInBackground(collectionID) { collection, e ->
            if (e == null) {
                // Now let's update it with some new data. In this case, only cheatMode and score
                // will get sent to the Parse Cloud. playerName hasn't changed.
                collection.put("rating", ratingNum)
                collection.saveInBackground()
                Log.i(TAG, "Rating bar of $collectionID is updated")
            } else {
                Log.i(TAG, "rating bar of $collectionID fails")
            }
        }
    }

    companion object{
        const val TAG="CollectionActivity"
    }
}