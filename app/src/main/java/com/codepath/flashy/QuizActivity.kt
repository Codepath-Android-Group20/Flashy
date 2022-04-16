package com.codepath.flashy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.codepath.flashy.models.Collection
import com.codepath.flashy.models.Flashcard
import com.codepath.flashy.models.Question
import com.parse.*
import org.w3c.dom.Text

class QuizActivity : AppCompatActivity() {
    private var tv_term: TextView? = null
    private var tv_progress: TextView? = null
    private var tv_option_one: TextView? = null
    private var tv_option_two: TextView? = null
    private var tv_option_three: TextView? = null
    private var tv_option_four: TextView? = null
    private var btn_submit: Button? = null

=======

class QuizActivity : AppCompatActivity() {
>>>>>>> origin/create-quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

<<<<<<< HEAD
        ParseObject.registerSubclass(Flashcard::class.java)
        queryFlashcards()

        ParseObject.registerSubclass(Collection::class.java)
        queryCollections()

        ParseObject.registerSubclass(Question::class.java)
        queryQuestion()

    }

    fun queryFlashcards(){
        val query: ParseQuery<Flashcard> = ParseQuery.getQuery(Flashcard::class.java)
        query.include(Flashcard.KEY_COLLECTION)
        query.findInBackground(object: FindCallback<Flashcard>{
            override fun done(flashcards: MutableList<Flashcard>?, e: ParseException?) {
                if (e!=null){
                    Log.e(MainActivity.TAG,"Error fetching flashcards")
                }else {
                    if (flashcards != null){
                        for (flashcard in flashcards){
                            Log.i(
                                MainActivity.TAG,"Front: " + flashcard.getFront() + " , Back: "+ flashcard.getBack()
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
                    Log.e(MainActivity.TAG,"Error fetching collections")
                }else {
                    if (collections != null){
                        for (collection in collections){
                            Log.i(
                                MainActivity.TAG,"Collection ID: " + collection.objectId + " , Author: "
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
        private const val TAG = "QuizActivity"
    }


=======


    }
>>>>>>> origin/create-quiz
}