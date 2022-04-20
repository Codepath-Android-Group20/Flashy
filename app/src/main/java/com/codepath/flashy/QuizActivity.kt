package com.codepath.flashy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import com.codepath.flashy.models.Collection
import com.codepath.flashy.models.Flashcard
import com.codepath.flashy.models.Question
import com.parse.*


class QuizActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    private var tv_term: TextView? = null
    private var tvProgress: TextView? = null
    private lateinit var btn_submit: Button
    private lateinit var btn_nextQuestion: Button
    private var mCurrentPosition: Int = 1 // THE DEFAULT CURRENT POSITION

    var mQuestionsList: ArrayList<Question> = arrayListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        progressBar = findViewById(R.id.progressBar)
        tv_term = findViewById(R.id.tv_term)
        tvProgress = findViewById(R.id.tv_progress)
        btn_submit = findViewById(R.id.btn_submit)
        btn_nextQuestion = findViewById(R.id.btn_nextQuestion)

//        ParseObject.registerSubclass(Question::class.java)
//        queryQuestion()
        val flashCards = intent.getParcelableArrayListExtra<Flashcard>("Collection")

        if (flashCards != null) {

            for (flashcard in flashCards) {
                var index = 1
                var term = flashcard.getFront()
                var description = flashcard.getBack()

                if (term != null && description != null) {
                    val question = Question(index, term, description)
                    mQuestionsList.add(question)
                }
                index++
            }
        }

        setQuestion()
        // Adding a click event for submit button. And Change the questions and check the select answers
        btn_submit.setOnClickListener {
            val question = mQuestionsList?.get(mCurrentPosition - 1)
            val typedAnswer = findViewById<EditText>(R.id.et_answer).text.toString()

            // check if the answer is wrong
            if (question.correctAnswer != typedAnswer) {
                Toast.makeText(this, "The answer is wrong", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "The answer is right", Toast.LENGTH_LONG).show()
            }

            if (mCurrentPosition == mQuestionsList!!.size) {
                btn_submit?.text = "FINISH"
            }
        }

        // move to next question
        btn_nextQuestion.setOnClickListener {
            mCurrentPosition++
            if (mCurrentPosition <= mQuestionsList!!.size) {
                setQuestion()
            }
        }

    }

    private fun setQuestion() {
        val question: Question = mQuestionsList!![mCurrentPosition - 1]

        // check if the position of the question is last then change the text of the button
        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit?.text = "FINISH"
            btn_submit?.text = "SUBMIT"
        }

        // update progressBar
        progressBar?.max = mQuestionsList!!.size
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition" + "/" + progressBar?.max

        // set the curremt question and the options in the UI
        tv_term?.text = question.term
    }

    companion object {
        private const val TAG = "QuizActivity"
    }
}