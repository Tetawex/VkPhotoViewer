package org.tetawex.vkphotoviewer.app.model.usecase

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetails

/**
 * Created by tetawex on 20.07.2018.
 */
interface FriendDetailsUseCase {
    fun getFriendsDetail(id: Long): Single<FriendDetails>
}