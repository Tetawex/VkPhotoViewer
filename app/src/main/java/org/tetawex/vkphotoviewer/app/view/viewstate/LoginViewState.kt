package org.tetawex.vkphotoviewer.app.view.viewstate

import org.tetawex.vkphotoviewer.app.view.abs.LoginView
import org.tetawex.vkphotoviewer.base.ViewState
import org.tetawex.vkphotoviewer.base.ViewWithProgressBarViewState

/**
 * Created by tetawex on 16.07.2018.
 */
class LoginViewState : ViewWithProgressBarViewState<LoginView>(), LoginView {
    override fun postUrl(url: String, postData: ByteArray) {
        submitCommand { it.postUrl(url = url, postData = postData) }
    }

    override fun setAuthResult(result: Int) {
        submitCommand { it.setAuthResult(result) }
    }

    override fun navigateToMainScreen() {
        runCommand { it.navigateToMainScreen() }
    }

    override fun loadUrl(url: String) {
        runCommand { it.loadUrl(url) }
    }
}