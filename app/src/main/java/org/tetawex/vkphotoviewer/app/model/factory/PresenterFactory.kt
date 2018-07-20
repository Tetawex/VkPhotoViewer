package org.tetawex.vkphotoviewer.app.model.factory

import org.tetawex.vkphotoviewer.app.presenter.LoginPresenter
import org.tetawex.vkphotoviewer.app.presenter.MainPresenter
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.app.view.router.RouterProvider

class PresenterFactory(private val interactorFactory: InteractorFactory,
                       private val viewStateFactory: ViewStateFactory,
                       private val routerProvider: RouterProvider<MainRouter>) {
    fun createLoginPresenter(): LoginPresenter {
        return LoginPresenter(
                interactorFactory.createAuthTokenInteractor(),
                interactorFactory.createLoginPostDataInteractor(),
                routerProvider,
                viewStateFactory.createLoginViewState())
    }

    fun createMainPresenter(): MainPresenter {
        return MainPresenter(
                viewStateFactory.createMainViewState(),
                routerProvider)
    }
}