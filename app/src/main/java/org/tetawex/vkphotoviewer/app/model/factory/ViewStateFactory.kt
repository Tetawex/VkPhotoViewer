package org.tetawex.vkphotoviewer.app.model.factory

import org.tetawex.vkphotoviewer.app.view.viewstate.FriendsDetailViewState
import org.tetawex.vkphotoviewer.app.view.viewstate.FriendsListViewState
import org.tetawex.vkphotoviewer.app.view.viewstate.LoginViewState
import org.tetawex.vkphotoviewer.app.view.viewstate.MainViewState

class ViewStateFactory {
    fun createMainViewState(): MainViewState {
        return MainViewState()
    }

    fun createLoginViewState(): LoginViewState {
        return LoginViewState()
    }

    fun createFriendsListViewState(): FriendsListViewState {
        return FriendsListViewState()
    }

    fun createFriendsDetailViewState(): FriendsDetailViewState {
        return FriendsDetailViewState()
    }
}