package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.model.factory.PresenterFactory
import org.tetawex.vkphotoviewer.base.PresenterManager

/**
 * Created by tetawex on 16.07.2018.
 */
class AppPresenterManager(private val presenterFactory: PresenterFactory)
    : PresenterManager() {
    override fun instantiatePresenterByTag(tag: String) {
        when (tag) {
            LOGIN_TAG -> {
                presenterMap[tag] = presenterFactory.createLoginPresenter()
            }
            MAIN_TAG -> {
                presenterMap[tag] = presenterFactory.createMainPresenter()//TODO!
            }
            FRIEND_LIST_TAG -> {
                presenterMap[tag] = presenterFactory.createFriendListPresenter()//TODO!
            }
            FRIEND_DETAILS_TAG -> {
                presenterMap[tag] = presenterFactory.createFriendDetailsPresenter()//TODO!
            }
        }
    }

    companion object {
        const val LOGIN_TAG = "login"
        const val MAIN_TAG = "main"
        const val FRIEND_LIST_TAG = "friend_list"
        const val FRIEND_DETAILS_TAG = "friend_details"
    }
}
