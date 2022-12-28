package com.uthus.test.presenter

import java.util.concurrent.ConcurrentHashMap

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
class SingleEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set
    /**
     * Returns the content and prevents it's used again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

}