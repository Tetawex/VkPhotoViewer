package org.tetawex.vkphotoviewer.app.model.interactor

import io.reactivex.Completable
import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.Repository

class AuthTokenUseCase(val repository: Repository,
                       val preferences: Preferences) : AuthTokenInteractor {
    override fun saveAccessToken(token: String, userId: String): Completable {
        return Completable.fromAction {
            preferences.saveAuthToken(token).commit()
        }
    }
}
