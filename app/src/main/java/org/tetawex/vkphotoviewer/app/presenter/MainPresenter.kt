package org.tetawex.vkphotoviewer.app.presenter

import org.tetawex.vkphotoviewer.app.view.abs.MainView
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.app.view.router.RouterProvider
import org.tetawex.vkphotoviewer.app.view.viewstate.MainViewState
import org.tetawex.vkphotoviewer.base.BasePresenter

/**
 * Created by tetawex on 18.07.2018.
 */
class MainPresenter(viewState: MainViewState,
                    routerProvider: RouterProvider<MainRouter>)
    : BasePresenter<MainView>(viewState) {

    override fun onFirstViewAttached() {
        viewRelay.showProgressbar()
    }
}