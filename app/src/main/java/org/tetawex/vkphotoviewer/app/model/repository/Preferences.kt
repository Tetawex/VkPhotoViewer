package org.tetawex.vkphotoviewer.app.model.repository

import org.tetawex.vkphotoviewer.app.model.AuthTokenProvider

/**
 * Created by tetawex on 17.07.2018.
 */
interface Preferences: AuthTokenProvider {
    fun saveAuthToken(token: String): Preferences
    fun commit()
    companion object {
        val PREFERENCES_NAME = "org.tetawex.vkphotoviewer"
    }
}