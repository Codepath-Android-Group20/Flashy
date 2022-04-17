package com.codepath.flashy

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.models.Flashcard
import org.w3c.dom.Text

class FlashcardAdapter (private val context: Context,
                        private val flashcards: ArrayList<Flashcard>)
                        : RecyclerView.Adapter<FlashcardAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardAdapter.ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.flash_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlashcardAdapter.ViewHolder, position: Int) {
        val flashcard= flashcards[position]
        holder.bind(flashcard)

    }

    override fun getItemCount(): Int {
        return flashcards.size
    }

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val tvKeyword: TextView
        val tvDescription: TextView

        init {
            tvKeyword = itemView.findViewById(R.id.tvKeyword)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            itemView.setOnClickListener{
                val intent = Intent(context, ViewCollectionActivity::class.java)
                intent.putParcelableArrayListExtra("Collection", flashcards)
                intent.putExtra("Count", adapterPosition)
                context.startActivity(intent)
            }

        }

        override fun onClick(p0: View?) {
            val flashcard= flashcards[adapterPosition]
            Toast.makeText(context,"" + adapterPosition, Toast.LENGTH_SHORT).show()


        }

        fun bind(flashcard: Flashcard) {
            tvKeyword.text=flashcard.getFront()
            tvDescription.text=flashcard.getBack()
        }
    }

    companion object{
        val TAG = "FlashcardAdapter"
    }
}