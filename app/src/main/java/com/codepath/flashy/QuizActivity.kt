package com.codepath.flashy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import com.codepath.flashy.models.Collection
import com.codepath.flashy.models.Flashcard
import com.codepath.flashy.models.Question
import com.parse.*


class QuizActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    private var tv_term: TextView? = null
    private var tvProgress: TextView? = null
    private var et_name: AppCompatEditText? = null
    private var btn_submit: Button? = null
    private var mCurrentPosition: Int = 1 // THE DEFAULT CURRENT POSITION
    private var mQuestionsList: ArrayList<Question>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        ParseObject.registerSubclass(Flashcard::class.java)
        queryFlashcards()

        ParseObject.registerSubclass(Collection::class.java)
        queryCollections()

        ParseObject.registerSubclass(Question::class.java)
        queryQuestion()

        tv_term = findViewById(R.id.tv_term)
        tvProgress = findViewById(R.id.tv_progress)
        et_name = findViewById(R.id.et_name)
        btn_submit = findViewById(R.id.btn_submit)
    }

    fun queryFlashcards() {
        val query: ParseQuery<Flashcard> = ParseQuery.getQuery(Flashcard::class.java)
        query.include(Flashcard.KEY_COLLECTION)
        query.findInBackground(object : FindCallback<Flashcard> {
            override fun done(flashcards: MutableList<Flashcard>?, e: ParseException?) {
                if (e != null) {
                    Log.e(MainActivity.TAG, "Error fetching flashcards")
                } else {
                    if (flashcards != null) {
                        for (flashcard in flashcards) {
                            Log.i(
                                MainActivity.TAG,
                                "Front: " + flashcard.getFront() + " , Back: " + flashcard.getBack()
                                        + " , Need To Learn: " + flashcard.getLearn() + ", From Collection (need to be fixed into a collection title): "
                                        + flashcard.getCollection().getTitle()
                            )
                        }
                    }
                }
            }
        })
    }

    fun queryCollections() {
        val query: ParseQuery<Collection> = ParseQuery.getQuery(Collection::class.java)
        query.include(Collection.KEY_AUTHOR)
        query.findInBackground(object : FindCallback<Collection> {
            override fun done(collections: MutableList<Collection>?, e: ParseException?) {
                if (e != null) {
                    Log.e(MainActivity.TAG, "Error fetching collections")
                } else {
                    if (collections != null) {
                        for (collection in collections) {
                            Log.i(
                                MainActivity.TAG,
                                "Collection ID: " + collection.objectId + " , Author: "
                                        + collection.getAuthor()?.username + " , Title: " + collection.getTitle()
                                        + " , Description: " + collection.getDescription() + " , Rating: " + collection.getRating()
                                        + " , Number of views: " + collection.getTimesViewed() + " , Times Downloaded: "
                                        + collection.getTimesDownloaded() + " ,CreatedAt: " + collection.createdAt
                            )
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
        query.findInBackground(object : FindCallback<Question> {
            override fun done(questions: MutableList<Question>?, e: ParseException?) {
                if (e != null) {
                    // something has went wrong
                    Log.e(TAG, "error fetching posts")
                } else {
                    if (questions != null) {
                        for (question in questions) {
                            Log.i(
                                TAG,
                                "Question: " + question.getTerm() + " Term: " + question.getFlashCard()
                                    .getFront()
                            )
                        }
                        mQuestionsList?.addAll(questions)

                    }
                }
            }
        })
    }

    fun setQuestion() {
        val question: Question = mQuestionsList!![mCurrentPosition - 1]

        // check if the position of the question is last then change the text of the submit button
        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit?.text = "FINISH"
        } else {
            btn_submit?.text = "SUBMIT"
        }

        // END
        progressBar?.progress =
            mCurrentPosition // Setting the current progress in the progressbar using the position of question
        tvProgress?.text =
            "$mCurrentPosition" + "/" + progressBar?.max // Setting up the progress text


        // setting the UI of the quiz screen


    }

    companion object {
        private const val TAG = "QuizActivity"
    }
}
