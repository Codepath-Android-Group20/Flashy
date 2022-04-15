package com.codepath.flashy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.models.Collection
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

class MyCollectionFragment : Fragment() {
    lateinit var rvCollection: RecyclerView
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
        rvCollection= view.findViewById(R.id.rvCollection)
        adapter= CollectionAdapter(requireContext(),displayCollections)
        rvCollection.adapter=adapter
        rvCollection.layoutManager= LinearLayoutManager(requireContext())
        queryCollection()

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