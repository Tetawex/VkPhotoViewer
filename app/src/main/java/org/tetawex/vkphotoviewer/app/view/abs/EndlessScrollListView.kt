package org.tetawex.vkphotoviewer.app.view.abs

import org.tetawex.vkphotoviewer.base.BaseView

interface EndlessScrollListView<I> : BaseView {
    fun appendList(items: List<I>)
    fun setList(items: List<I>)
    fun clearList()
}