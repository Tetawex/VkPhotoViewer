package org.tetawex.vkphotoviewer.app.model.factory

import org.tetawex.vkphotoviewer.app.model.usecase.*
import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.Repository

class InteractorFactory(private val repository: Repository,
                        private val preferences: Preferences) {
    fun createAuthTokenInteractor(): AuthTokenUseCase {
        return AuthTokenInteractor(repository, preferences)
    }

    fun createLoginPostDataInteractor(): LoginPostDataUseCase {
        return LoginPostDataInteractor()
    }

    fun createFriendsDetailInteractor(): FriendDetailsUseCase {
        return FriendDetailsInteractor(repository)
    }

    fun createFriendsListInteractor(): FriendListUseCase {
        return FriendListInteractor(repository)
    }
}