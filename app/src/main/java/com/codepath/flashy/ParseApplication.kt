package com.codepath.flashy

import android.app.Application
import com.codepath.flashy.models.Collection
import com.codepath.flashy.models.Flashcard
import com.codepath.flashy.models.Question
import com.parse.Parse
import com.parse.ParseObject

class ParseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // Register your parse models
        ParseObject.registerSubclass(Collection::class.java)
        ParseObject.registerSubclass(Question::class.java)
        ParseObject.registerSubclass(Flashcard::class.java)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());
    }
}