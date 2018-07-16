package org.tetawex.vkphotoviewer.base

/**
 * Created by Tetawex on 15.02.2018.
 */

abstract class BasePresenter<V : BaseView> {
    //Used for state management
    val viewState: ViewState<V>
    //Used to relay commands to view
    val viewRelay: V

    abstract fun createViewState(): ViewState<V>

    init {
        val state = createViewState()
        viewState = state
        viewRelay = state as V
    }

    abstract fun onFirstViewAttached()

    fun onViewAttached(view: V) {
        viewState.attachView(view)
        viewState.restoreState(view)
    }

    fun onViewDetached() {
        viewState.detachView()
    }
}
