package org.tetawex.vkphotoviewer.app.model.interactor

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetail

class FriendDetailsUseCase(private val repository: Repository) : FriendDetailsInteractor {
    override fun getFriendsDetail(id: Long): Single<FriendDetail> {
        return repository.getFriendDetailsById(id)
    }
}