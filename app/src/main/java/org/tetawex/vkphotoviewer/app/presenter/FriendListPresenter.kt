package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.model.interactor.FriendListInteractor
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.app.view.abs.FriendListView
import org.tetawex.vkphotoviewer.app.view.viewstate.FriendListViewState
import org.tetawex.vkphotoviewer.base.BasePresenter

/**
 * Created by tetawex on 20.07.2018.
 */
class FriendListPresenter(val interactor: FriendListInteractor,
                          viewState: FriendListViewState) :
        BasePresenter<FriendListView>(viewState) {
    override fun onFirstViewAttached() {
    }

    fun onOpenFriendsDetail(friendsListItem: FriendsListItem) {

    }
}