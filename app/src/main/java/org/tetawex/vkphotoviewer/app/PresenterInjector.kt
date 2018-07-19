package org.tetawex.vkphotoviewer.app

import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.interactor.AccessTokenUseCase
import org.tetawex.vkphotoviewer.app.model.interactor.LoginPostDataUseCase
import org.tetawex.vkphotoviewer.app.presenter.LoginPresenter

/**
 * Created by tetawex on 17.07.2018.
 * Injects required dependencies to presenters
 */
class PresenterInjector(private var repository: Repository,
                        private var preferences: Preferences) {
    fun inject(presenter: LoginPresenter) {
        presenter.userCodeInteractor = AccessTokenUseCase(repository, preferences)
        presenter.loginPostDataInteractor = LoginPostDataUseCase()
    }
}