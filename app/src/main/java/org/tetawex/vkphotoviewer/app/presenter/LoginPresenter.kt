package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.model.repository.Config
import org.tetawex.vkphotoviewer.app.model.interactor.AccessTokenInteractor
import org.tetawex.vkphotoviewer.app.model.interactor.LoginPostDataInteractor
import org.tetawex.vkphotoviewer.app.view.abs.LoginView
import org.tetawex.vkphotoviewer.app.view.viewstate.LoginViewState
import org.tetawex.vkphotoviewer.base.BasePresenter
import org.tetawex.vkphotoviewer.base.ViewState
import org.tetawex.vkphotoviewer.base.util.rxextensions.*

/**
 * Created by tetawex on 16.07.2018.
 */
class LoginPresenter : BasePresenter<LoginView>() {
    override fun createViewState(): ViewState<LoginView> {
        return LoginViewState()
    }

    lateinit var userCodeInteractor: AccessTokenInteractor
    lateinit var loginPostDataInteractor: LoginPostDataInteractor

    init {
        App.instance.presenterInjector.inject(this)
    }

    override fun onFirstViewAttached() {
        val url = Config.AUTH_ENDPOINT
        viewRelay.postUrl(url, loginPostDataInteractor
                .getPostDataUrl()
                .toByteArray()
        )
    }

    fun onTokenReceived(token: String, userId: String) {
        viewRelay.showProgressbar()
        userCodeInteractor.saveAccessToken(token, userId)
                .applySchedulers()
                .subscribe({
                    viewRelay.setAuthResult(-1) //OK
                    viewRelay.navigateToMainScreen()
                }, { throwable ->
                    viewRelay.setAuthResult(0) //CANCELED
                    viewRelay.hideProgressbar()
                    viewRelay.showError(throwable)
                })
    }
}
