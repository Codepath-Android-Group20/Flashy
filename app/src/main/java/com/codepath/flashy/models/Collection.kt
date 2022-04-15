package com.codepath.flashy.models

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("Collection")
class Collection: ParseObject(){

    //collection id
    fun getCollectionId (): String?{
        return getString(KEY_COLLECTION_ID)
    }
    fun setCollectionId(collectionId: String){
        put(KEY_COLLECTION_ID,collectionId)
    }

    //author
    fun getAuthor (): ParseUser?{
        return getParseUser(KEY_AUTHOR)
    }
    fun setAuthor(author: ParseUser){
        put(KEY_AUTHOR,author)
    }

    //collection's title
    fun getTitle(): String? {
        return getString(KEY_TITLE)
    }
    fun setTitle(title: String) {
        put(KEY_TITLE,title)
    }

    //description
    fun getDescription(): String?{
        return getString(KEY_DESCRIPTION)
    }
    fun setDescription(description: String){
        put(KEY_DESCRIPTION,description)
    }

    //rating
    fun getRating(): Double?{
        return getDouble(KEY_RATING)
    }
    fun setRating(rating: Double){
        put(KEY_RATING,rating)
    }

    //Times Viewed
    fun getTimesViewed():Int?{
        return getInt(KEY_TIMES_VIEWED)
    }
    fun setTimesViewed(views: Int){
        put(KEY_TIMES_VIEWED,views)
    }

    //Times Downloaded
    fun getTimesDownloaded(): Int?{
        return getInt(KEY_TIMES_DOWNLOADED)
    }
    fun setTimesDownloaded(downloads: Int){
        put(KEY_TIMES_DOWNLOADED,downloads)
    }

    companion object{
        const val KEY_COLLECTION_ID = "objectId"
        const val KEY_AUTHOR = "author"
        const val KEY_TITLE = "name"
        const val KEY_DESCRIPTION = "description"
        const val KEY_RATING = "rating"
        const val KEY_TIMES_VIEWED = "timesViewed"
        const val KEY_TIMES_DOWNLOADED = "timesDownloaded"
    }
}