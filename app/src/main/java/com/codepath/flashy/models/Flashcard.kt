package com.codepath.flashy.models

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("Flashcard")
class Flashcard: ParseObject() {

    //FRONT
    fun getFront(): String? {
        return getString(KEY_FRONT)
    }

    fun setFront(front: String){
        put(KEY_FRONT,front)
    }

    //BACK
    fun getBack(): String? {
        return getString(KEY_BACK)
    }
    fun setBack(back: String) {
        put(KEY_BACK, back)
    }

    //LEARN
    fun getLearn(): Boolean{
        return getBoolean(KEY_LEARN)
    }
    fun setLearn(learn: Boolean) {
        put(KEY_LEARN,learn)
    }

    //    Collection
    fun getCollection(): Collection {
        return getParseObject(KEY_COLLECTION) as Collection
    }

    fun setCollection(collection: Collection){
        put (KEY_COLLECTION,collection)
    }
    fun getCollectionID(): String? {
        return getString(KEY_COLLECTION_ID)
    }

    fun setCollectionID(collectionID:String){
        put (KEY_COLLECTION_ID,collectionID)
    }






    companion object{
        const val KEY_FRONT = "Front"
        const val KEY_BACK = "Back"
        const val KEY_LEARN = "needToLearn"
        const val KEY_COLLECTION = "collection"
        const val KEY_COLLECTION_ID = "collectionID"
    }
}