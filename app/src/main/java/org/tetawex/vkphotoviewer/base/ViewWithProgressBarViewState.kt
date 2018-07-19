package org.tetawex.vkphotoviewer.base

/**
 * Created by tetawex on 18.07.2018.
 */
open class ViewWithProgressBarViewState<V : BaseView> : ViewState<V>(), BaseView {
    override fun hideProgressbar() {
        submitCommand { it.hideProgressbar() }
    }

    override fun showProgressbar() {
        submitCommand { it.showProgressbar() }
    }
}