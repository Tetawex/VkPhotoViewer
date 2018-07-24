package org.tetawex.vkphotoviewer.app.model.usecase

import io.reactivex.Completable
import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.Repository

class AuthTokenInteractor(val repository: Repository,
                          val preferences: Preferences) : AuthTokenUseCase {
    override fun saveAccessToken(token: String, userId: String): Completable {
        return Completable.fromAction {
            preferences.saveAuthToken(token).commit()
        }
    }
}
