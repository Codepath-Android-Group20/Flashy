package com.codepath.flashy

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.models.Flashcard

class FlashcardViewAdapter (private val context: Context,
                        private val flashcards: ArrayList<Flashcard>)
    : BaseAdapter() {
    override fun getCount(): Int {
        return flashcards.size
    }

    override fun getItem(p0: Int): Any {
        return flashcards[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view:View
        if(p1==null)
            view= LayoutInflater.from(context).inflate(R.layout.item_koloda, p2, false)
        else
            view=p1

        return view
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val tvFlashcard: TextView
        init {
            tvFlashcard = itemView.findViewById(R.id.tvFlashcard)
        }
        fun bind(flashcard: Flashcard) {
            tvFlashcard.text=flashcard.getFront()
        }

    }


}

