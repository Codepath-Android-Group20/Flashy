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
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.codepath.flashy.*
import com.codepath.flashy.models.Collection
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

open class OnlineCollectionFragment : Fragment() {
    lateinit var rvAllCollections: RecyclerView
    lateinit var adapter: OtherUsersCollectionAdapter
    lateinit var swipeContainer: SwipeRefreshLayout
    var displayCollections:MutableList<Collection> = mutableListOf<Collection>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_online_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAllCollections= view.findViewById(R.id.rvAllCollections)

        adapter= OtherUsersCollectionAdapter(requireContext(), displayCollections)
        rvAllCollections.adapter=adapter
        rvAllCollections.layoutManager= LinearLayoutManager(requireContext())


        // Setup refresh listener which triggers new data loading
        // locate the swip container view
        swipeContainer = view.findViewById(R.id.swipeContainer)

        swipeContainer.setOnRefreshListener {
            // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.
            Log.i(TAG, "refreshing timeline")
            displayCollections.clear()
            queryCollection()
        }

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        );

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
//                        for (collection in collections){
//                            Log.i(
//                                MainActivity.TAG,"Collection ID: " + collection.objectId + " , Author: "
//                                        + collection.getAuthor()?.username + " , Title: " +  collection.getTitle()
//                                        + " , Description: " + collection.getDescription() + " , Rating: " + collection.getRating()
//                                        + " , Number of views: " + collection.getTimesViewed() + " , Times Downloaded: "
//                                        + collection.getTimesDownloaded() + " ,CreatedAt: " + collection.createdAt)
//
//                        }
                        var mycollections: MutableList<Collection> = mutableListOf()
                        for (i in 0..collections.size -1){
                            if (collections[i] != null && collections[i].getAuthor()!!.get("username") == ParseUser.getCurrentUser().get("username")){
                                mycollections.add(collections[i])


                            }
                        }
                        displayCollections.addAll(collections)

                        adapter.notifyDataSetChanged()
                        swipeContainer.isRefreshing = false
                    }
                }
            }
        })
    }

    companion object {
        const val TAG = "MyCollectionFragment"
    }
}