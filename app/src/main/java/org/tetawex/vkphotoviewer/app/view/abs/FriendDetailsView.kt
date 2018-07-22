package org.tetawex.vkphotoviewer.app.view.abs

import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetails
import org.tetawex.vkphotoviewer.base.BaseView

/**
 * Created by tetawex on 19.07.2018.
 */
interface FriendDetailsView : BaseView {
    fun setDetails(details: FriendDetails)
}