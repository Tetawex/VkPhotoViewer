package org.tetawex.vkphotoviewer.app.model.usecase

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetails

class FriendDetailsInteractor(private val repository: Repository) : FriendDetailsUseCase {
    override fun getFriendsDetail(id: Long): Single<FriendDetails> {
        return repository.getFriendDetailsById(id)
    }
}