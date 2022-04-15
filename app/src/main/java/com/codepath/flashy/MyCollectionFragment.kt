package com.codepath.flashy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.flashy.models.Collection

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
        TODO("Not yet implemented")
    }


}