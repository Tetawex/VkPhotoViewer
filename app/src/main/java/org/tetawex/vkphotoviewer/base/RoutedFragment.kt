package org.tetawex.vkphotoviewer.base

import android.app.Application

abstract class RoutedFragment<V : BaseView, P : BasePresenter<V>, R : Router, A : Application> : BaseFragment<V, P, A>() {
    val router: R
        get() {
            try {
                return activity as R
            } catch (e: ClassCastException) {
                throw ClassCastException("Activity does not implement the declared router interface").also { it.printStackTrace() }
            }
        }
    //Called when router performs goBack() while
    abstract fun onGoneBackFromThisScreen()
}