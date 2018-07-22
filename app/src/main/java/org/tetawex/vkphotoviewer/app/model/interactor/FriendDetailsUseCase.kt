package org.tetawex.vkphotoviewer.app.model.interactor

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetails

class FriendDetailsUseCase(private val repository: Repository) : FriendDetailsInteractor {
    override fun getFriendsDetail(id: Long): Single<FriendDetails> {
        return repository.getFriendDetailsById(id)
    }
}