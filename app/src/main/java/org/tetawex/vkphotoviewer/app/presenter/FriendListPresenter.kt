package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.model.interactor.FriendListInteractor
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.app.view.abs.FriendListView
import org.tetawex.vkphotoviewer.app.view.viewstate.FriendListViewState
import org.tetawex.vkphotoviewer.base.BasePresenter
import org.tetawex.vkphotoviewer.base.util.rxextensions.applySchedulers

/**
 * Created by tetawex on 20.07.2018.
 */
class FriendListPresenter(val friendListInteractor: FriendListInteractor,
                          viewState: FriendListViewState) :
        BasePresenter<FriendListView>(viewState) {
    override fun onFirstViewAttached() {
        friendListInteractor
                .getFriendsList(0, 10)
                .applySchedulers()
                .subscribe(
                        { list ->
                            viewRelay.appendList(list)
                        },
                        { t -> viewRelay.showError(t) }
                )
    }

    fun onOpenFriendDetails(friendsListItem: FriendsListItem) {

    }
}