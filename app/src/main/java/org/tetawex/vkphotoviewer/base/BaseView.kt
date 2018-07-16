package org.tetawex.vkphotoviewer.base

/**
 * Created by tetawex on 26.02.2018.
 */
interface BaseView {
    fun showError(t: Throwable)
    //Signals that view state should be dropped
    fun dispose()

    fun showProgressbar()
    fun hideProgressBar()
}
