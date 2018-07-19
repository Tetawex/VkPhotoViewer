package org.tetawex.vkphotoviewer.app.model.repository

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetails
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsFeedItem

/**
 * Created by tetawex on 17.07.2018.
 */
interface Repository {
    fun getFriendsFeed(offset: Int, count: Int): Single<List<FriendsFeedItem>>
    fun getFriendDetailsById(id: Long): Single<FriendDetails>
}