package com.codepath.flashy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.models.Collection

class CollectionAdapter(private val context: Context,
                        private val collections: ArrayList<Collection>): RecyclerView.Adapter<CollectionAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: CollectionAdapter.ViewHolder, position: Int) {
        val item: String? = collections[position].getTitle()

        holder.textView.text = item
    }


    override fun getItemCount(): Int {
        return collections.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)
            itemView.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            val collection = collections[adapterPosition]
            Toast.makeText(context,collection.getTitle(), Toast.LENGTH_SHORT).show()
        }
    }
}