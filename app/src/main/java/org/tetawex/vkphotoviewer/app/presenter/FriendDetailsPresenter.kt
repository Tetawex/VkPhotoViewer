package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.model.interactor.FriendDetailsInteractor
import org.tetawex.vkphotoviewer.app.view.abs.FriendDetailsView
import org.tetawex.vkphotoviewer.app.view.viewstate.FriendDetailsViewState
import org.tetawex.vkphotoviewer.base.BasePresenter

/**
 * Created by tetawex on 20.07.2018.
 */
class FriendDetailsPresenter(private val friendDetailsInteractor: FriendDetailsInteractor,
                             viewState: FriendDetailsViewState) :
        BasePresenter<FriendDetailsView>(viewState) {
    override fun onFirstViewAttached() {
        viewRelay.loadDefaultData()
    }
}