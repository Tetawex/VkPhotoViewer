package org.tetawex.vkphotoviewer.app

import android.app.Application
import android.content.Context
import android.content.Intent
import org.tetawex.vkphotoviewer.app.model.factory.InteractorFactory
import org.tetawex.vkphotoviewer.app.model.factory.PresenterFactory
import org.tetawex.vkphotoviewer.app.model.factory.ViewStateFactory
import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.PreferencesImpl
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.RestRepository
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.app.view.router.RouterProvider
import org.tetawex.vkphotoviewer.app.view.ui.MainActivity

import org.tetawex.vkphotoviewer.base.PresenterManager

/**
 * Created by Tetawex on 15.02.2018.
 */

class App : Application(), RouterProvider<MainRouter> {

    lateinit var presenterManager: PresenterManager
    private lateinit var repository: Repository
    private lateinit var preferences: Preferences

    var mainRouter: MainRouter? = null
    override val router: MainRouter
    //Should only be called from the ui thread from a fragment/ activity
        get() {
            //I really doubt this condition might be true
            if (mainRouter == null)
                startActivity(Intent(this, MainActivity::class.java))
            //It won't work cuz sA is async
            return mainRouter!!
        }

    override fun onCreate() {
        super.onCreate()

        //Init repository and prefs
        preferences = PreferencesImpl(
                sharedPreferences = applicationContext.getSharedPreferences(
                        Preferences.PREFERENCES_NAME,
                        Context.MODE_PRIVATE))
        repository = RestRepository(preferences)

        //Init factories
        val interactorFactory = InteractorFactory(repository, preferences)
        val viewStateFactory = ViewStateFactory()
        val presenterFactory = PresenterFactory(interactorFactory, viewStateFactory, this)

        presenterManager = AppPresenterManager(presenterFactory)
    }
}
