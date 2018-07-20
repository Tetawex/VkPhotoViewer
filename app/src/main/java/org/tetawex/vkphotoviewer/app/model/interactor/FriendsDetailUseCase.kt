package org.tetawex.vkphotoviewer.app.model.interactor

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsDetail

class FriendsDetailUseCase(private val repository: Repository) : FriendsDetailInteractor {
    override fun getFriendsDetail(id: Long): Single<FriendsDetail> {
        return repository.getFriendDetailsById(id)
    }
}