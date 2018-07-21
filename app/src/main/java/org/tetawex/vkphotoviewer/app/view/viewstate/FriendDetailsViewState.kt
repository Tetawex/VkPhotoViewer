package org.tetawex.vkphotoviewer.app.view.viewstate

import org.tetawex.vkphotoviewer.app.view.abs.FriendDetailsView
import org.tetawex.vkphotoviewer.base.ViewWithProgressBarViewState

/**
 * Created by tetawex on 16.07.2018.
 */
class FriendDetailsViewState : ViewWithProgressBarViewState<FriendDetailsView>(), FriendDetailsView {
    override fun loadDefaultData() {
        submitCommand { it.loadDefaultData() }
    }

    override fun loadPhoto(url: String) {
        submitCommand { it.loadPhoto(url) }
    }

}