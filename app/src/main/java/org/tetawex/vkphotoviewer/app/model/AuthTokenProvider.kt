package org.tetawex.vkphotoviewer.app.model

/**
 * Created by tetawex on 18.07.2018.
 */
interface AuthTokenProvider {
    fun getAuthToken(): String
}