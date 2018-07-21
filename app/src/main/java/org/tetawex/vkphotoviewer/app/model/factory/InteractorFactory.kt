package org.tetawex.vkphotoviewer.app.model.factory

import org.tetawex.vkphotoviewer.app.model.interactor.*
import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.Repository

class InteractorFactory(private val repository: Repository,
                        private val preferences: Preferences) {
    fun createAuthTokenInteractor(): AuthTokenInteractor {
        return AuthTokenUseCase(repository, preferences)
    }

    fun createLoginPostDataInteractor(): LoginPostDataInteractor {
        return LoginPostDataUseCase()
    }

    fun createFriendsDetailInteractor(): FriendDetailsInteractor {
        return FriendDetailsUseCase(repository)
    }

    fun createFriendsListInteractor(): FriendListInteractor {
        return FriendListUseCase(repository)
    }
}