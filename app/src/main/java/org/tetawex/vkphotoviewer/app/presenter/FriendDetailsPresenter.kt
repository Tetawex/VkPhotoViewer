package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.model.interactor.FriendDetailsInteractor
import org.tetawex.vkphotoviewer.app.view.abs.FriendDetailsView
import org.tetawex.vkphotoviewer.app.view.viewstate.FriendDetailsViewState
import org.tetawex.vkphotoviewer.base.BasePresenter
import org.tetawex.vkphotoviewer.base.util.rxextensions.applySchedulers

/**
 * Created by tetawex on 20.07.2018.
 */
class FriendDetailsPresenter(private val friendDetailsInteractor: FriendDetailsInteractor,
                             viewState: FriendDetailsViewState) :
        BasePresenter<FriendDetailsView>(viewState) {
    //Yes it's a band-aid as it violates mvp dumb-view rule, the variable should be assigned via
    //factory instead of being set by the view
    var userId = 0L

    override fun onFirstViewAttached() {
        friendDetailsInteractor
                .getFriendsDetail(userId)
                .applySchedulers()
                .subscribe(
                        {
                            viewRelay.setDetails(it)
                        },
                        {
                            viewRelay.showError(it)
                        })
    }
}