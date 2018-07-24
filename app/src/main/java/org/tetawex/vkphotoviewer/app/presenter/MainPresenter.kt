package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.model.usecase.AuthTokenUseCase
import org.tetawex.vkphotoviewer.app.model.usecase.LoginPostDataUseCase
import org.tetawex.vkphotoviewer.app.view.abs.MainView
import org.tetawex.vkphotoviewer.app.view.viewstate.MainViewState
import org.tetawex.vkphotoviewer.base.BasePresenter

/**
 * Created by tetawex on 18.07.2018.
 */
class MainPresenter(private val userCodeUseCase: AuthTokenUseCase,
                    private val loginPostDataUseCase: LoginPostDataUseCase,
                    viewState: MainViewState)
    : BasePresenter<MainView>(viewState) {

    override fun onFirstViewAttached() {
        viewRelay.openLoginScreen()
    }
}