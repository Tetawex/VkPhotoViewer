package org.tetawex.vkphotoviewer.app.view.ui

import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.view_progressbar.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.presenter.LoginPresenter
import org.tetawex.vkphotoviewer.app.view.abs.LoginView
import org.tetawex.vkphotoviewer.base.BaseFragment
import org.tetawex.vkphotoviewer.base.PresenterManager


class FriendsFeedFragment : BaseFragment<LoginView, LoginPresenter, App>(), LoginView {

    companion object {
        val fragmentTag = AppPresenterManager.FRIENDS_FEED_TAG
        fun newInstance(): FriendsFeedFragment = FriendsFeedFragment()
    }


    override val presenterTag = AppPresenterManager.FRIENDS_FEED_TAG
    override val presenterManager: PresenterManager = app.presenterManager
    override val layoutId = R.layout.fragment_friends_feed

    override fun setupViews(view: View): View {
        return view
    }

    override fun preInit() {
    }

    override fun postInit() {
    }

    override fun showProgressbar() {
        progressbar.visibility = android.view.View.VISIBLE
    }

    override fun hideProgressbar() {
        progressbar.visibility = android.view.View.GONE
    }

    fun showError(errorId: Int) {
        Toast.makeText(activity, getString(errorId), Toast.LENGTH_SHORT).show()
    }

    override fun loadUrl(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postUrl(url: String, postData: ByteArray) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAuthResult(result: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

