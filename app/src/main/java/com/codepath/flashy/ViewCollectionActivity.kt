package com.codepath.flashy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.*
import com.codepath.flashy.models.Flashcard





class ViewCollectionActivity : AppCompatActivity() {

    fun flip(View: TextView){
        val animationFlip = AnimationUtils.loadAnimation(this, R.anim.flip)
        View.startAnimation(animationFlip)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_collection)
        val flashcardList= intent.getParcelableArrayListExtra<Flashcard>("Collection")

        if (flashcardList != null) {
            var count = 0
            var currentFlashcard = flashcardList[count]
            var isFrontShown = true
            var frontText = currentFlashcard.getFront()
            var backText = currentFlashcard.getBack()
            var showing =findViewById<TextView>(R.id.tVFlashcard)

            showing.setText(frontText)
            findViewById<TextView>(R.id.tvCardCount).setText ("" + (count + 1) + "/" + (flashcardList.size))


            findViewById<TextView>(R.id.tVFlashcard).setOnClickListener {
                if (isFrontShown){
                    flip(showing)
                    showing.setText(backText)
                    isFrontShown = false
                }
                else {
                    findViewById<TextView>(R.id.tVFlashcard).setText(frontText)
                    flip(showing)
                    isFrontShown = true
                }
            }

            findViewById<ImageView>(R.id.ivNextButton).setOnClickListener {
                if (count < flashcardList.size - 1){
                    count ++
                    currentFlashcard = flashcardList.get(count)

                    frontText = currentFlashcard.getFront()
                    backText = currentFlashcard.getBack()
                    findViewById<TextView>(R.id.tVFlashcard).setText(frontText)
                    findViewById<TextView>(R.id.tvCardCount).setText ("" + (count + 1) + "/" + (flashcardList.size))
                }
                else{
                    Toast.makeText(this,"End of List", Toast.LENGTH_SHORT).show()
                }
            }

            findViewById<ImageView>(R.id.ivPrevButton).setOnClickListener {
                if (count != 0){
                    count --
                    currentFlashcard = flashcardList.get(count)
                    frontText = currentFlashcard.getFront()
                    backText = currentFlashcard.getBack()
                    findViewById<TextView>(R.id.tVFlashcard).setText(frontText)
                    findViewById<TextView>(R.id.tvCardCount).setText ("" + (count + 1) + "/" + (flashcardList.size))
                }
                else{
                    Toast.makeText(this,"Start of List", Toast.LENGTH_SHORT).show()
                }
            }



        }

    }


}







