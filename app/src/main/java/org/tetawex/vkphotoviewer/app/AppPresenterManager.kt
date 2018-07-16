package org.tetawex.vkphotoviewer.app

import org.tetawex.vkphotoviewer.base.PresenterManager

/**
 * Created by tetawex on 16.07.2018.
 */
class AppPresenterManager : PresenterManager() {
    override fun instantiatePresenterByTag(tag: String) {
        when (tag) {
            LOGIN_TAG -> {
                presenterMap.put(tag, LoginPresenter())
            }
        }
    }

    companion object {
        val LOGIN_TAG = "login"
    }
}
