package org.tetawex.vkphotoviewer.base.util.viewextensions

import android.view.View

/**
 * Created by tetawex on 20.07.2018.
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}