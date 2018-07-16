package org.tetawex.vkphotoviewer.app

import org.tetawex.vkphotoviewer.base.BasePresenter
import org.tetawex.vkphotoviewer.base.ViewState

/**
 * Created by tetawex on 16.07.2018.
 */
class LoginPresenter : BasePresenter<LoginView>() {
    override fun createViewState(): ViewState<LoginView> = LoginViewState()

    override fun onFirstViewAttached() {
        viewRelay.hideProgressBar()
    }
}