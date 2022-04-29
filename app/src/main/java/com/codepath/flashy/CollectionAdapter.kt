package com.codepath.flashy

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.models.Collection
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery


const val COLLECTION_ID_EXTRA ="COLLECTION_EXTRA"
const val COLLECTION_TITLE_EXTRA ="COLLECTION_NAME_EXTRA"

class CollectionAdapter(private val context: Context,
                        private val collections: MutableList<Collection>): RecyclerView.Adapter<CollectionAdapter.ViewHolder>()  {
    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionAdapter.ViewHolder {
//        val context = parent.context
//        val inflater = LayoutInflater.from(context)
//        // Inflate the custom layout
//        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
//        // Return a new holder instance
//        return ViewHolder(view)

        val view= LayoutInflater.from(context).inflate(R.layout.collection, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionAdapter.ViewHolder, position: Int) {
        val collection = collections[position]

        holder.bind(collection)

    }


    override fun getItemCount(): Int {
        return collections.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener,View.OnLongClickListener {
        val tv_collectionName: TextView
        val ratingBar: RatingBar
        init {
            tv_collectionName = itemView.findViewById(R.id.tv_collectionName)
            ratingBar = itemView.findViewById(R.id.rbRating)
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)

        }



        override fun onClick(p0: View?) {
            val collection = collections[adapterPosition]

            val intent = Intent(context,CollectionActivity::class.java)
            intent.putExtra(COLLECTION_ID_EXTRA, collection.objectId)
            intent.putExtra(COLLECTION_TITLE_EXTRA, collection.getTitle())
            val s:Int = collections.size

            context.startActivity(intent)



        }



        fun bind(collection: Collection) {
            tv_collectionName.text = collection.getTitle()
            ratingBar.rating = collection.getRating()?.toFloat()!!
        }

        override fun onLongClick(p0: View?): Boolean {

            val collection = collections[adapterPosition]


            var builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Collection")
            builder.setMessage("Are you sure you want to delete this collection? All flashcards within this collection will also be deleted.")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->


                val query2 = ParseQuery<ParseObject>("Flashcard")
                query2.whereContains("collectionID", collection.objectId)


                query2.findInBackground { objects: List<ParseObject>?, e: ParseException? ->

                    if (e == null) {
                        if (objects != null && objects.isNotEmpty()) {
                            for (i in 0..objects.size) {
                                objects[0].deleteInBackground()
                            }
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


                val query = ParseQuery<ParseObject>("Collection")
                query.whereContains("objectId", collection.objectId)

                query.findInBackground { objects: List<ParseObject>?, e: ParseException? ->

                    if (e == null) {
                        if (objects != null) {
                            objects[0].deleteInBackground()

                        }

                    } else {
                        if (objects != null) {
                            //Toast.makeText(this, objects.size, Toast.LENGTH_SHORT).show()
                        }
                    }
                }




                collections.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)


















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
        const val TAG= "CollectionAdapter"
    }
}