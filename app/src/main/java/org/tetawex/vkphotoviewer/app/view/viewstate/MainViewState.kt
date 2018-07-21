package org.tetawex.vkphotoviewer.app.view.viewstate

import org.tetawex.vkphotoviewer.app.view.abs.MainView
import org.tetawex.vkphotoviewer.base.ViewState

/**
 * Created by tetawex on 18.07.2018.
 */
class MainViewState : ViewState<MainView>(), MainView {
    override fun openDefaultScreen() {
        runCommand { it.openDefaultScreen() }
    }

    override fun openLoginScreen() {
        runCommand { it.openLoginScreen() }
    }

    override fun hideProgressbar() {
        submitCommand { it.hideProgressbar() }
    }

    override fun showProgressbar() {
        submitCommand { it.showProgressbar() }
    }
}