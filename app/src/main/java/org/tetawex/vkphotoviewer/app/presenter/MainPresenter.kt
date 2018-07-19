package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.view.abs.MainView
import org.tetawex.vkphotoviewer.app.view.viewstate.MainViewState
import org.tetawex.vkphotoviewer.base.BasePresenter
import org.tetawex.vkphotoviewer.base.ViewState

/**
 * Created by tetawex on 18.07.2018.
 */
class MainPresenter : BasePresenter<MainView>() {
    override fun createViewState(): ViewState<MainView> {
        return MainViewState()
    }

    override fun onFirstViewAttached() {

    }
}