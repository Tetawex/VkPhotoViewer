package org.tetawex.vkphotoviewer.app.model.repository

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsDetail
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem

/**
 * Created by tetawex on 17.07.2018.
 */
interface Repository {
    fun getFriendsFeed(offset: Int, count: Int): Single<List<FriendsListItem>>
    fun getFriendDetailsById(id: Long): Single<FriendsDetail>
}