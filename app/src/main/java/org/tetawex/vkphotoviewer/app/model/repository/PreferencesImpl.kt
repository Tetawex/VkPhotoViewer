package org.tetawex.vkphotoviewer.app.model.repository

import android.content.SharedPreferences

/**
 * Created by tetawex on 17.07.2018.
 */
class PreferencesImpl(private val sharedPreferences: SharedPreferences) : Preferences {

    var currentEditor: SharedPreferences.Editor? = null

    override fun commit() {
        currentEditor?.commit()
        currentEditor = null
    }

    override fun saveAuthToken(token: String): Preferences {
        if (currentEditor == null)
            currentEditor = sharedPreferences.edit()

        currentEditor?.run {
            putString(PREF_KEY_TOKEN, token)
        }
        return this
    }

    override fun getAuthToken(): String {
        return sharedPreferences.getString(PREF_KEY_TOKEN, "")
    }


    companion object {
        val PREF_KEY_TOKEN = "token"
    }
}