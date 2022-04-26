package com.codepath.flashy.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.CollectionAdapter
import com.codepath.flashy.CreateCollectionActivity
import com.codepath.flashy.MainActivity
import com.codepath.flashy.R
import com.codepath.flashy.models.Collection
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

open class MyCollectionFragment : Fragment() {
    lateinit var rvAllCollections: RecyclerView
    lateinit var adapter: CollectionAdapter

    var displayCollections:ArrayList<Collection> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAllCollections= view.findViewById(R.id.rvAllCollections)
        adapter= CollectionAdapter(requireContext(),displayCollections)
        rvAllCollections.adapter=adapter
        rvAllCollections.layoutManager= LinearLayoutManager(requireContext())
        queryCollection()

        view.findViewById<ImageButton>(R.id.ibAddCollection).setOnClickListener {
            val intent = Intent(requireContext(), CreateCollectionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun queryCollection() {
        val query: ParseQuery<Collection> = ParseQuery.getQuery(Collection::class.java)
        query.include(Collection.KEY_AUTHOR)
        query.findInBackground(object: FindCallback<Collection> {
            override fun done(collections: MutableList<Collection>?, e: ParseException?) {
                if (e!=null){
                    Log.e(MainActivity.TAG,"Error fetching collections")
                }else {
                    if (collections != null){
                        displayCollections.addAll(collections)
                        adapter.notifyDataSetChanged()

//                        for (collection in collections){
//                            Log.i(
//                                MainActivity.TAG,"Collection ID: " + collection.objectId + " , Author: "
//                                    + collection.getAuthor()?.username + " , Title: " +  collection.getTitle()
//                                    + " , Description: " + collection.getDescription() + " , Rating: " + collection.getRating()
//                                    + " , Number of views: " + collection.getTimesViewed() + " , Times Downloaded: "
//                                    + collection.getTimesDownloaded() + " ,CreatedAt: " + collection.createdAt)

//                        }
                    }
                }
            }
        })
    }


}