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

//    get Collection's Title
    fun getCollectionTitle(): ParseObject? {
        return getParseObject(KEY_COLLECTION)
    }

    fun setCollectionTitle(collection: ParseObject){
        put(KEY_COLLECTION,collection)
    }


    companion object{
        const val KEY_FRONT = "Front"
        const val KEY_BACK = "Back"
        const val KEY_LEARN = "needToLearn"
        const val KEY_COLLECTION = "collection"
    }
}