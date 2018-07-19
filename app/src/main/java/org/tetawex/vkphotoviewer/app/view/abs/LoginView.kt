package org.tetawex.vkphotoviewer.app.view.abs

import org.tetawex.vkphotoviewer.base.BaseView

/**
 * Created by tetawex on 16.07.2018.
 */
interface LoginView : BaseView {
    fun loadUrl(url: String)
    fun postUrl(url: String, postData: ByteArray)

    fun setAuthResult(result: Int)
    fun navigateToMainScreen()
}