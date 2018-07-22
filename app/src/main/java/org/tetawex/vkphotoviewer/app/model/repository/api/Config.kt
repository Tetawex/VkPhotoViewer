package org.tetawex.vkphotoviewer.app.model.repository.api

/**
 * Created by tetawex on 17.07.2018.
 */

object Config {
    val AUTH_ENDPOINT = "https://oauth.vk.com/authorize"
    val REDIRECT_URI = "https://oauth.vk.com/blank.html"
    val CLIENT_ID = "6639885"
    val API_VERSION = "5.80"
    val ENCODING = "UTF-8"

    //val MASK_CHAT = 4096
    //val MASK_WALL = 8192
    val MASK_FRIENDS = 2
    val MASK_OFFLINE = 65536

    val MASK_FULL = MASK_FRIENDS+ MASK_OFFLINE
}