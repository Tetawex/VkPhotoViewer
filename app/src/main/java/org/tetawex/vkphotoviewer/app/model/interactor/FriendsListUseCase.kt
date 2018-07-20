package org.tetawex.vkphotoviewer.app.model.interactor

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem

class FriendsListUseCase(private val repository: Repository) : FriendsListInteractor {
    override fun getFriendsList(offset: Int, count: Int): Single<List<FriendsListItem>> {
        return repository.getFriendsFeed(offset, count)
    }
}