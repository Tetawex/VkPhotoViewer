package org.tetawex.vkphotoviewer.app

import org.tetawex.vkphotoviewer.app.LoginView
import org.tetawex.vkphotoviewer.base.ViewState

/**
 * Created by tetawex on 16.07.2018.
 */
class LoginViewState : ViewState<LoginView>(), LoginView {
    override fun hideProgressBar() {
        submitCommand { it.hideProgressBar() }
    }

    override fun showProgressbar() {
        submitCommand { it.showProgressbar() }
    }
}