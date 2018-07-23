package org.tetawex.vkphotoviewer.app.model.interactor

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem

class FriendListUseCase(private val repository: Repository) : FriendListInteractor {
    override fun getFriendList(offset: Int, count: Int): Single<List<FriendsListItem>> {
        return repository.getFriendList(offset, count)
    }
}