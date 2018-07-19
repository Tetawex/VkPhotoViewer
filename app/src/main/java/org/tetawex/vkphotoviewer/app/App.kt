package org.tetawex.vkphotoviewer.app

import android.app.Application
import android.content.Context
import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.PreferencesImpl
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.RestRepository
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.view.router.MainRouter

import org.tetawex.vkphotoviewer.base.PresenterManager

/**
 * Created by Tetawex on 15.02.2018.
 */

class App : Application() {

    lateinit var presenterManager: PresenterManager
    lateinit var presenterInjector: PresenterInjector
    lateinit var repository: Repository
    lateinit var preferences: Preferences

    private var mainRouterStub = object : MainRouter {
        override fun goBack() {
        }

        override fun navigateToLoginScreen() {
        }

        override fun navigateToFriendsFeedScreen() {
        }

        override fun navigateToFriendDetailsScreen() {
        }

    }
    private var mainRouter: MainRouter? = null
    fun getActiveMainRouter(): MainRouter {
        return if (mainRouter != null)
            mainRouter as MainRouter
        else
            mainRouterStub
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        //TODO move to a separate class
        presenterManager = AppPresenterManager()
        preferences = PreferencesImpl(
                sharedPreferences = applicationContext.getSharedPreferences(
                        Preferences.PREFERENCES_NAME,
                        Context.MODE_PRIVATE))
        repository = RestRepository(preferences)
        presenterInjector = PresenterInjector(repository, preferences)
    }

    companion object {
        lateinit var instance: App
    }
}
