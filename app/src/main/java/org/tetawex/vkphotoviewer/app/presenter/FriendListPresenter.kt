package org.tetawex.vkphotoviewer.app.presenter

import io.reactivex.disposables.Disposables
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
    var friendListDisposable = Disposables.empty()
    override fun onFirstViewAttached() {
        onRefreshList(true)
    }

    fun onOpenFriendDetails(friendsListItem: FriendsListItem) {
        viewRelay.openFriendDetails(friendsListItem.id, friendsListItem.fullName, friendsListItem.photoUrl)
    }

    fun onRefreshList(showProgressBar: Boolean) {
        if (showProgressBar)
            viewRelay.showProgressbar()
        friendListDisposable.dispose()
        friendListDisposable = friendListInteractor
                .getFriendList(0, 10000)
                .applySchedulers()
                .doFinally {
                    viewRelay.hideProgressbar()
                }
                .subscribe(
                        { list ->
                            viewRelay.setList(list)
                        },
                        { t -> viewRelay.showError(t) }
                )
    }
}