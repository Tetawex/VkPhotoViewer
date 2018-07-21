package org.tetawex.vkphotoviewer.app.model.interactor

import io.reactivex.Completable

/**
 * Created by tetawex on 17.07.2018.
 */

interface AuthTokenInteractor {
    fun saveAccessToken(token: String, userId: String): Completable
}