package org.tetawex.vkphotoviewer.app.model.usecase

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem

/**
 * Created by tetawex on 20.07.2018.
 */
interface FriendListUseCase {
    fun getFriendList(offset: Int, count: Int): Single<List<FriendsListItem>>
}