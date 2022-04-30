package com.codepath.flashy

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.models.Flashcard
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
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

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView), View.OnClickListener,View.OnLongClickListener{
        val tvKeyword: TextView
        val tvDescription: TextView

        init {
            tvKeyword = itemView.findViewById(R.id.tvKeyword)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)

        }

        override fun onClick(p0: View?) {
            val flashcard= flashcards[adapterPosition]
            val intent = Intent(context,ViewCollectionActivity::class.java);
            intent.putParcelableArrayListExtra("Collection", flashcards)
            intent.putExtra("Number", adapterPosition)
            startActivity(context,intent,null)
        }

        fun bind(flashcard: Flashcard) {
            tvKeyword.text=flashcard.getFront()
            tvDescription.text=flashcard.getBack()
        }

        override fun onLongClick(p0: View?): Boolean {


            val flashcard= flashcards[adapterPosition]


            var builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Flashcard")
            builder.setMessage("Are you sure you want to delete this flashcard?")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->


                val query2 = ParseQuery<ParseObject>("Flashcard")
                query2.whereContains("collectionID", flashcard.getCollectionID())


                query2.findInBackground { objects: List<ParseObject>?, e: ParseException? ->

                    if (e == null) {
                        if (objects != null && objects.isNotEmpty()) {

                            objects[adapterPosition].deleteInBackground()
                            flashcards.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            Toast.makeText(context, "Flashcard Deleted", Toast.LENGTH_SHORT)

                            //  displayedFlashcards.clear()
                            // queryFlascard()
                            // flashcardAdapter.notifyDataSetChanged()


                        }

                    } else {
                        if (objects != null) {
                            Toast.makeText(context, "Error with deletion", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
















                dialog.cancel()
            })
            builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            var alert = builder.create()
            alert.show()

            return true
        }
        }


    companion object{
        val TAG = "FlashcardAdapter"
    }
}