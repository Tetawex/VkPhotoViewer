package org.tetawex.vkphotoviewer.app.model.repository

/**
 * Created by tetawex on 18.07.2018.
 */
interface AuthTokenProvider {
    fun getAuthToken(): String
}