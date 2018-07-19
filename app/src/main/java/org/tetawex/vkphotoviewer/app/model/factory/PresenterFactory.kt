package org.tetawex.vkphotoviewer.app.model.factory

import org.tetawex.vkphotoviewer.app.presenter.LoginPresenter

class PresenterFactory(private val interactorFactory: InteractorFactory,
                       private val viewStateFactory: ViewStateFactory) {
    fun createLoginPresenter(): LoginPresenter {
        return LoginPresenter(
                interactorFactory.createAuthTokenInteractor(),
                interactorFactory.createLoginPostDataInteractor(),
                viewStateFactory.createLoginViewState())
    }
}