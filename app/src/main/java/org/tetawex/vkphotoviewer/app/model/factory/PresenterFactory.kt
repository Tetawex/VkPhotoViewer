package org.tetawex.vkphotoviewer.app.model.factory

import org.tetawex.vkphotoviewer.app.presenter.FriendDetailsPresenter
import org.tetawex.vkphotoviewer.app.presenter.FriendListPresenter
import org.tetawex.vkphotoviewer.app.presenter.LoginPresenter
import org.tetawex.vkphotoviewer.app.presenter.MainPresenter

class PresenterFactory(private val interactorFactory: InteractorFactory,
                       private val viewStateFactory: ViewStateFactory) {
    fun createLoginPresenter(): LoginPresenter {
        return LoginPresenter(
                interactorFactory.createAuthTokenInteractor(),
                interactorFactory.createLoginPostDataInteractor(),
                viewStateFactory.createLoginViewState())
    }

    fun createMainPresenter(): MainPresenter {
        return MainPresenter(
                interactorFactory.createAuthTokenInteractor(),
                interactorFactory.createLoginPostDataInteractor(),
                viewStateFactory.createMainViewState())
    }

    fun createFriendListPresenter(): FriendListPresenter {
        return FriendListPresenter(
                interactorFactory.createFriendsListInteractor(),
                viewStateFactory.createFriendsListViewState())
    }

    fun createFriendDetailsPresenter(): FriendDetailsPresenter {
        return FriendDetailsPresenter(
                interactorFactory.createFriendsDetailInteractor(),
                viewStateFactory.createFriendsDetailViewState())
    }
}