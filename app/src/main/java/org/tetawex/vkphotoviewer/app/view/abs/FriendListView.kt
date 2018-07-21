package org.tetawex.vkphotoviewer.app.view.abs

import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem

/**
 * Created by tetawex on 19.07.2018.
 */
interface FriendListView : EndlessScrollListView<FriendsListItem> {
    fun openFriendDetails(id: Long, fullName: String, photoPreviewUrl: String)
}