package org.tetawex.vkphotoviewer.app.view.viewstate

import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetails
import org.tetawex.vkphotoviewer.app.view.abs.FriendDetailsView
import org.tetawex.vkphotoviewer.base.ViewWithProgressBarViewState

/**
 * Created by tetawex on 16.07.2018.
 */
class FriendDetailsViewState : ViewWithProgressBarViewState<FriendDetailsView>(), FriendDetailsView {
    override fun setDetails(details: FriendDetails) {
        submitCommand { it.setDetails(details) }
    }
}