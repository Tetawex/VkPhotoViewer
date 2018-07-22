package org.tetawex.vkphotoviewer.app.view.ui

import android.graphics.Bitmap
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
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.base.RoutedFragment
import org.tetawex.vkphotoviewer.base.util.viewextensions.hide
import org.tetawex.vkphotoviewer.base.util.viewextensions.show


class LoginFragment : RoutedFragment<LoginView, LoginPresenter, MainRouter, App>(), LoginView {
    override fun onGoneBackFromThisScreen() {
    }

    override fun finishLogin() {
        router.navigateToFriendListScreen()
    }

    companion object {
        val fragmentTag = AppPresenterManager.LOGIN_TAG
        fun newInstance(): LoginFragment = LoginFragment()
    }


    override val presenterTag = AppPresenterManager.LOGIN_TAG
    override val layoutId = R.layout.fragment_login

    override fun setupViews(view: View): View {
        return view
    }

    override fun preInit() {
        presenterManager = app.presenterManager
        swipe_refresh.setOnRefreshListener {
            presenter.onPageRefreshed()
        }
    }

    override fun postInit() {
        setupWebView()
    }

    lateinit var webViewClient: WebViewClient

    private var hasUrlLoaded: Boolean = false


    /*override fun onBackPressed() {
        /*if (web_view.canGoBack())
            web_view.goBack()
        else
            super.onBackPressed()*/
    }*/

    private fun setupWebView() {
        web_view.isVerticalScrollBarEnabled = false
        web_view.isHorizontalScrollBarEnabled = false

        val settings = web_view.settings
        settings.displayZoomControls = false
        settings.setSupportZoom(false)
        //settings.javaScriptEnabled = true //VK provides js-free page cuz safety and stuff
        settings.domStorageEnabled = true
        settings.cacheMode = WebSettings.LOAD_NO_CACHE //Avoid cache-related issues

        webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                presenter.urlLoaded()
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Log.e("url", url)
                hasUrlLoaded = true
                if (url.contains("#access_token=") && url.contains("user_id=")) {
                    val params = url.split("#", "&")
                    presenter.onTokenReceived(
                            token = params[1].split("=")[1],
                            userId = params[3].split("=")[1])
                    return true
                }
                return false
            }
        }

        web_view.webViewClient = webViewClient
    }

    override fun showProgressbar() {
        progressbar.show()
    }

    override fun hideProgressbar() {
        progressbar.hide()
        swipe_refresh.isRefreshing = false
    }

    fun showError(errorId: Int) {
        Toast.makeText(activity, getString(errorId), Toast.LENGTH_SHORT).show()
    }

    override fun loadUrl(url: String) {
        web_view.loadUrl(url)
    }

    override fun postUrl(url: String, postData: ByteArray) {
        web_view.postUrl(url, postData)
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            webViewClient.
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
    }*/
}

