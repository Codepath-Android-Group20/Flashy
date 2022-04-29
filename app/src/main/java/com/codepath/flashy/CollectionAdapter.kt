package com.codepath.flashy

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.models.Collection

const val COLLECTION_ID_EXTRA ="COLLECTION_EXTRA"
const val COLLECTION_TITLE_EXTRA ="COLLECTION_NAME_EXTRA"

class CollectionAdapter(private val context: Context,
                        private val collections: MutableList<Collection>): RecyclerView.Adapter<CollectionAdapter.ViewHolder>()  {

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

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val tv_collectionName: TextView
        val ratingBar: RatingBar
        init {
            tv_collectionName = itemView.findViewById(R.id.tv_collectionName)
            ratingBar = itemView.findViewById(R.id.rbRating)
            itemView.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            val collection = collections[adapterPosition]
            val intent = Intent(context,CollectionActivity::class.java)
            intent.putExtra(COLLECTION_ID_EXTRA, collection.objectId)
            intent.putExtra(COLLECTION_TITLE_EXTRA, collection.getTitle())
            context.startActivity(intent)
        }

        fun bind(collection: Collection) {
            tv_collectionName.text = collection.getTitle()
            ratingBar.rating = collection.getRating()?.toFloat()!!
        }
    }
    companion object{
        const val TAG= "CollectionAdapter"
    }
}