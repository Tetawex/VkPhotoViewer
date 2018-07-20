package org.tetawex.vkphotoviewer.app.view.viewstate

import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.app.view.abs.FriendsDetailView
import org.tetawex.vkphotoviewer.app.view.abs.FriendsListView
import org.tetawex.vkphotoviewer.app.view.abs.LoginView
import org.tetawex.vkphotoviewer.base.ViewState
import org.tetawex.vkphotoviewer.base.ViewWithProgressBarViewState

/**
 * Created by tetawex on 16.07.2018.
 */
class FriendsDetailViewState : ViewWithProgressBarViewState<FriendsDetailView>(), FriendsDetailView {
    override fun loadPhoto(url: String) {
        submitCommand { it.loadPhoto(url) }
    }

}