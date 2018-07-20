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
                presenterMap.put(tag, presenterFactory.createLoginPresenter())
            }
            MAIN_TAG -> {
                presenterMap.put(tag, presenterFactory.createMainPresenter())//TODO!
            }
            FRIENDS_LIST_TAG -> {
                presenterMap.put(tag, presenterFactory.createMainPresenter())//TODO!
            }
            FRIENDS_DETAIL_TAG -> {
                presenterMap.put(tag, presenterFactory.createMainPresenter())//TODO!
            }
        }
    }

    companion object {
        val LOGIN_TAG = "login"
        val MAIN_TAG = "main"
        val FRIENDS_LIST_TAG = "friends_list"
        val FRIENDS_DETAIL_TAG = "friends_detail"
    }
}
