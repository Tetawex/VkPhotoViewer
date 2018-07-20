package org.tetawex.vkphotoviewer.app.model.interactor

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsDetail

/**
 * Created by tetawex on 20.07.2018.
 */
interface FriendsDetailInteractor {
    fun getFriendsDetail(id: Long): Single<FriendsDetail>
}