package org.tetawex.vkphotoviewer.app.view.abs

import org.tetawex.vkphotoviewer.base.BaseView

/**
 * Created by tetawex on 19.07.2018.
 */
interface FriendDetailsView : BaseView {
    fun loadPhoto(url: String)
    fun loadDefaultData()
}