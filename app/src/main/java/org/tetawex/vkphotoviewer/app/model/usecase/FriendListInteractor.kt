package org.tetawex.vkphotoviewer.app.model.usecase

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem

class FriendListInteractor(private val repository: Repository) : FriendListUseCase {
    override fun getFriendList(offset: Int, count: Int): Single<List<FriendsListItem>> {
        return repository.getFriendList(offset, count)
    }
}