package org.tetawex.vkphotoviewer.app.model.interactor

import io.reactivex.Completable
import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.Repository

class AccessTokenUseCase(val repository: Repository,
                         val preferences: Preferences) : AccessTokenInteractor {
    override fun saveAccessToken(token: String, userId: String): Completable {
        return Completable.fromAction {
            preferences.saveAuthToken(token).commit()
        }
    }
    /*override fun login(username: String, password: String): Completable {
        return repository
                .getToken(username, password)
                .compose(RxUtils.applySingleSchedulers())
                .doOnSuccess({ token ->
                    userAuthManager.login(username, token)
                })
                .toCompletable()
    }*/
}
