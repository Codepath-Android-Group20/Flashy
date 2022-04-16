package com.codepath.flashy.models

import com.bumptech.glide.load.model.stream.QMediaStoreUriLoader
import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("Question")
class Question: ParseObject() {

    fun getTerm(): String? {
        return getString(TERM_NAME)
    }

    fun setTerm(term: String) {
        put(TERM_NAME, term)
    }

    fun getOption1(): String? {
        return getString(OPTION1)
    }

    fun setOption1(option1: String) {
        put(OPTION1, option1)
    }

    fun getOption2(): String? {
        return getString(OPTION2)
    }

    fun setOption2(option2: String) {
        put(OPTION2, option2)
    }

    fun getOption3(): String? {
        return getString(OPTION3)
    }
    fun setOption3(option3: String) {
        put(OPTION3, option3)
    }

    fun getOption4(): String? {
        return getString(OPTION4)
    }

    fun setOption4(option4: String) {
        put(OPTION4, option4)
    }

    fun getCorrectionAnswer(): Int {
        return getInt(CORRECT_ANS)
    }

    fun setCorrectAnswer(correctAnswer: Int) {
        put(CORRECT_ANS, correctAnswer)
    }

    fun getFlashCard(): Flashcard {
        return getParseObject(FLASHCARD) as Flashcard
    }

    fun setFlashCard(flashcard: Flashcard) {
        put(FLASHCARD, flashcard)
    }

    companion object {
        const val QUESTION = "question"
        const val TERM_NAME = "term"
        const val OPTION1 = "option1"
        const val OPTION2 = "option2"
        const val OPTION3 = "option3"
        const val OPTION4 = "option4"
        const val FLASHCARD = "flashcard"
        const val CORRECT_ANS = "correctAnswer"
    }

}