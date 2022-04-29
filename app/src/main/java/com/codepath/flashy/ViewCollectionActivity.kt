package com.codepath.flashy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.*
import com.codepath.flashy.models.Flashcard
import com.yalantis.library.Koloda


class ViewCollectionActivity : AppCompatActivity() {
    lateinit var koloda: Koloda
    fun flip(View: TextView){
        val animationFlip = AnimationUtils.loadAnimation(this, R.anim.flip)
        View.startAnimation(animationFlip)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_collection)
        val flashcardList= intent.getParcelableArrayListExtra<Flashcard>("Collection")
        var number = intent.getIntExtra("Number",0)

        if (flashcardList != null) {
            var count = number
            var currentFlashcard = flashcardList[count]
            var isFrontShown = true
            var frontText = currentFlashcard.getFront()
            var backText = currentFlashcard.getBack()
            var showing =findViewById<TextView>(R.id.tvFlashcard)

            showing.setText(frontText)
            findViewById<TextView>(R.id.tvCardCount).setText ("" + (count + 1) + "/" + (flashcardList.size))


            findViewById<TextView>(R.id.tvFlashcard).setOnClickListener {
                if (isFrontShown){
                    flip(showing)
                    showing.setText(backText)
                    isFrontShown = false
                }
                else {
                    findViewById<TextView>(R.id.tvFlashcard).setText(frontText)
                    flip(showing)
                    isFrontShown = true
                }
            }
            koloda= findViewById(R.id.koloda)
//            koloda.onButtonClick(false){
//                if (count < flashcardList.size - 1){
//                    count ++
//                    currentFlashcard = flashcardList.get(count)
//
//                    frontText = currentFlashcard.getFront()
//                    backText = currentFlashcard.getBack()
//                    findViewById<TextView>(R.id.tVFlashcard).setText(frontText)
//                    findViewById<TextView>(R.id.tvCardCount).setText ("" + (count + 1) + "/" + (flashcardList.size))
//                }
//                else{
//                    Toast.makeText(this,"End of List", Toast.LENGTH_SHORT).show()
//                }
//            }

//            findViewById<ImageView>(R.id.ivPrevButton).setOnClickListener {
//                if (count != 0){
//                    count --
//                    currentFlashcard = flashcardList.get(count)
//                    frontText = currentFlashcard.getFront()
//                    backText = currentFlashcard.getBack()
//                    findViewById<TextView>(R.id.tVFlashcard).setText(frontText)
//                    findViewById<TextView>(R.id.tvCardCount).setText ("" + (count + 1) + "/" + (flashcardList.size))
//                }
//                else{
//                    Toast.makeText(this,"Start of List", Toast.LENGTH_SHORT).show()
//                }
//            }



        }

    }


}







