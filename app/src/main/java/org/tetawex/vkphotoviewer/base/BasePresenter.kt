package org.tetawex.vkphotoviewer.base

/**
 * Created by Tetawex on 15.02.2018.
 */

abstract class BasePresenter<V : BaseView>(
        //Used for state management
        private val viewState: ViewState<V>) {

    //Used to relay commands to view
    protected val viewRelay: V = viewState as V

    abstract fun onFirstViewAttached()

    fun onViewAttached(view: V) {
        viewState.attachView(view)
        viewState.restoreState(view)
    }

    fun onViewDetached() {
        viewState.detachView()
    }
}
