package org.tetawex.vkphotoviewer.base

import org.tetawex.vkphotoviewer.base.BasePresenter
import java.util.HashMap

/**
 * Created by tetawex on 26.02.2018.
 */

abstract class PresenterManager {
    protected var presenterMap: MutableMap<String, BasePresenter<*>> = HashMap<String, BasePresenter<*>>(1)

    fun getPresenter(tag: String): BasePresenter<*> {
        if (!presenterMap.containsKey(tag)) {
            instantiatePresenterByTag(tag)
        }
        return presenterMap[tag]!!
    }

    fun disposePresenter(tag: String) {
        presenterMap.remove(tag)
    }

    protected abstract fun instantiatePresenterByTag(tag: String)

}
