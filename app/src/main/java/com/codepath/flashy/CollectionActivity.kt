package com.codepath.flashy

import android.content.Intent
import android.media.Rating
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.models.Collection
import com.codepath.flashy.models.Flashcard
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class CollectionActivity : AppCompatActivity() {
    lateinit var collectionID: String
    lateinit var rvFlashcard: RecyclerView
    lateinit var flashcardAdapter:FlashcardAdapter
    lateinit var collection: Collection
    val displayedFlashcards:ArrayList<Flashcard> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        rvFlashcard= findViewById(R.id.rvFlashcard)
        flashcardAdapter=FlashcardAdapter(this, displayedFlashcards)
        collectionID = intent.getStringExtra(COLLECTION_ID_EXTRA).toString()
        val collectionTitle = intent.getStringExtra(COLLECTION_TITLE_EXTRA).toString()
        collection = intent.getParcelableExtra<Collection>(COLLECTION_OBJECT_EXTRA) as Collection
        findViewById<EditText>(R.id.etCollectionName).setText(collectionTitle)
//        if (collection!=null)
        Log.i(TAG, "collection is $collectionID")
//        else
//            Log.i(TAG, "No collection title")
        rvFlashcard.adapter = flashcardAdapter
        rvFlashcard.layoutManager = LinearLayoutManager(this)

        queryFlascard()

        findViewById<Button>(R.id.btnView).setOnClickListener {
            val intent = Intent(this,ViewCollectionActivity::class.java);
            intent.putParcelableArrayListExtra("Collection", displayedFlashcards)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_quiz).setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putParcelableArrayListExtra("Collection", displayedFlashcards)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val front = findViewById<EditText>(R.id.etFront).text.toString()
            val back = findViewById<EditText>(R.id.etBack).text.toString()
            if (front.isNotEmpty() && back.isNotEmpty()) {
                createFlashCard(front, back, true, collectionID)
            } else {
                Toast.makeText(this, "Front and Back must not be empty", Toast.LENGTH_SHORT).show()
            }
        }
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
    companion object{
        const val TAG="CollectionActivity"
    }
}