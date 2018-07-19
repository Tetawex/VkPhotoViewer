package org.tetawex.vkphotoviewer.app.model.factory

import org.tetawex.vkphotoviewer.app.model.interactor.AccessTokenInteractor
import org.tetawex.vkphotoviewer.app.model.interactor.AccessTokenUseCase
import org.tetawex.vkphotoviewer.app.model.interactor.LoginPostDataInteractor
import org.tetawex.vkphotoviewer.app.model.interactor.LoginPostDataUseCase
import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.Repository

class InteractorFactory(private val repository: Repository,
                        private val preferences: Preferences) {
    fun createAuthTokenInteractor(): AccessTokenInteractor {
        return AccessTokenUseCase(repository, preferences)
    }

    fun createLoginPostDataInteractor(): LoginPostDataInteractor {
        return LoginPostDataUseCase()
    }
}