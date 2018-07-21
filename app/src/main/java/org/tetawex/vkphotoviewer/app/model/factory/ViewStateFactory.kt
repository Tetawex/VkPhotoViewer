package org.tetawex.vkphotoviewer.app.model.factory

import org.tetawex.vkphotoviewer.app.view.viewstate.FriendDetailsViewState
import org.tetawex.vkphotoviewer.app.view.viewstate.FriendListViewState
import org.tetawex.vkphotoviewer.app.view.viewstate.LoginViewState
import org.tetawex.vkphotoviewer.app.view.viewstate.MainViewState

class ViewStateFactory {
    fun createMainViewState(): MainViewState {
        return MainViewState()
    }

    fun createLoginViewState(): LoginViewState {
        return LoginViewState()
    }

    fun createFriendsListViewState(): FriendListViewState {
        return FriendListViewState()
    }

    fun createFriendsDetailViewState(): FriendDetailsViewState {
        return FriendDetailsViewState()
    }
}