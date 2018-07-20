package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.model.interactor.AccessTokenInteractor
import org.tetawex.vkphotoviewer.app.model.interactor.LoginPostDataInteractor
import org.tetawex.vkphotoviewer.app.model.repository.Config
import org.tetawex.vkphotoviewer.app.view.abs.LoginView
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.app.view.router.RouterProvider
import org.tetawex.vkphotoviewer.app.view.viewstate.LoginViewState
import org.tetawex.vkphotoviewer.base.BasePresenter
import org.tetawex.vkphotoviewer.base.util.rxextensions.applySchedulers

/**
 * Created by tetawex on 16.07.2018.
 */
class LoginPresenter(private val userCodeInteractor: AccessTokenInteractor,
                     private val loginPostDataInteractor: LoginPostDataInteractor,
                     viewState: LoginViewState,
                     private val routerProvider: RouterProvider<MainRouter>)
    : BasePresenter<LoginView>(viewState) {

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
                    routerProvider.router.navigateToFriendsFeedScreen()
                }, { throwable ->
                    viewRelay.setAuthResult(0) //CANCELED
                    viewRelay.hideProgressbar()
                    viewRelay.showError(throwable)
                })
    }
}
