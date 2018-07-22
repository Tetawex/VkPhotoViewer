package org.tetawex.vkphotoviewer.app.view.router

import android.view.View
import org.tetawex.vkphotoviewer.base.Router

/**
 * Created by tetawex on 19.07.2018.
 */
interface MainRouter : Router {
    fun navigateToLoginScreen()
    fun navigateToFriendListScreen()
    fun navigateToFriendDetailsScreen(id: Long, name: String, photoPreviewUrl: String, transitionViews: List<View>)
}