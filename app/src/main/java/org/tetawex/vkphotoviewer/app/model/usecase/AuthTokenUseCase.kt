package org.tetawex.vkphotoviewer.app.model.usecase

import io.reactivex.Completable

/**
 * Created by tetawex on 17.07.2018.
 */

interface AuthTokenUseCase {
    fun saveAccessToken(token: String, userId: String): Completable
}