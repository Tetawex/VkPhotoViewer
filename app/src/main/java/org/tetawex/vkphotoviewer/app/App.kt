package org.tetawex.vkphotoviewer.app

import android.app.Application

import org.tetawex.vkphotoviewer.base.PresenterManager

/**
 * Created by Tetawex on 15.02.2018.
 */

class App : Application() {

    lateinit var presenterManager: PresenterManager

    override fun onCreate() {
        super.onCreate()
        instance = this

        //TODO move to a separate class
        presenterManager = AppPresenterManager()
        //repository = new RestRepository();
    }

    companion object {
        //private Repository repository;
        lateinit var instance: App
    }

    //public Repository getRepository() {
    //   return repository;
    //}
}
