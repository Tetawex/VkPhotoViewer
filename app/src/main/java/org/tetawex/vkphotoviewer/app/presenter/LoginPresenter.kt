package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.model.usecase.AuthTokenUseCase
import org.tetawex.vkphotoviewer.app.model.usecase.LoginPostDataUseCase
import org.tetawex.vkphotoviewer.app.model.repository.api.Config
import org.tetawex.vkphotoviewer.app.view.abs.LoginView
import org.tetawex.vkphotoviewer.app.view.viewstate.LoginViewState
import org.tetawex.vkphotoviewer.base.BasePresenter
import org.tetawex.vkphotoviewer.base.util.rxextensions.applySchedulers

/**
 * Created by tetawex on 16.07.2018.
 */
class LoginPresenter(private val userCodeUseCase: AuthTokenUseCase,
                     private val loginPostDataUseCase: LoginPostDataUseCase,
                     viewState: LoginViewState)
    : BasePresenter<LoginView>(viewState) {

    override fun onFirstViewAttached() {
        postUrlInView()
    }

    fun onPageRefreshed() {
        postUrlInView()
    }

    fun onTokenReceived(token: String, userId: String) {
        viewRelay.showProgressbar()
        userCodeUseCase.saveAccessToken(token, userId)
                .applySchedulers()
                .subscribe({
                    viewRelay.finishLogin()
                }, { throwable ->
                    viewRelay.hideProgressbar()
                    viewRelay.showError(throwable)

                })
    }

    fun urlLoaded() {
        viewRelay.hideProgressbar()
    }


    private fun postUrlInView() {
        viewRelay.showProgressbar()
        viewRelay.postUrl(Config.AUTH_ENDPOINT, loginPostDataUseCase
                .getPostDataUrl()
                .toByteArray()
        )
    }

}
