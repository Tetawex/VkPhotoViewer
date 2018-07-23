package org.tetawex.vkphotoviewer.app.model.repository.api

import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class WebClient : WebRequestPerformer {
    override fun performWebRequest(url: URL): String {
        val urlConnection = url.openConnection() as HttpURLConnection
        val bufferedInputStream = BufferedInputStream(urlConnection.inputStream)
        val scanner = Scanner(bufferedInputStream).useDelimiter("\\A")

        urlConnection.disconnect()
        return if (scanner.hasNext()) scanner.next() else ""
    }
}