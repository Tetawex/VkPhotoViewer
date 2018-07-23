package org.tetawex.vkphotoviewer.app.model.repository.api

import java.net.URL

interface WebRequestPerformer {
    fun performWebRequest(url: URL): String
}