package org.tetawex.vkphotoviewer.base

import java.util.*

/**
 * Created by tetawex on 26.02.2018.
 */

abstract class PresenterManager {
    protected var presenterMap: MutableMap<String, BasePresenter<*>> = HashMap<String, BasePresenter<*>>(1)

    fun <P> getPresenter(tag: String): P {
        if (!presenterMap.containsKey(tag)) {
            instantiatePresenterByTag(tag)
        }
        return presenterMap[tag]!! as P
    }

    fun disposePresenter(tag: String) {
        presenterMap.remove(tag)
    }

    protected abstract fun instantiatePresenterByTag(tag: String)

}
