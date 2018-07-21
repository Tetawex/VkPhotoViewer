package org.tetawex.vkphotoviewer.app.view.ui

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_friends_detail.*
import kotlinx.android.synthetic.main.view_progressbar.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.presenter.FriendDetailsPresenter
import org.tetawex.vkphotoviewer.app.view.abs.FriendDetailsView
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.base.RoutedFragment
import org.tetawex.vkphotoviewer.base.bitmap.BitmapTransformer
import org.tetawex.vkphotoviewer.base.bitmap.CircularBitmapTransformer
import org.tetawex.vkphotoviewer.base.bitmap.ImageLoadManager
import org.tetawex.vkphotoviewer.base.util.viewextensions.hide
import org.tetawex.vkphotoviewer.base.util.viewextensions.show


class FriendDetailsFragment : RoutedFragment<FriendDetailsView, FriendDetailsPresenter, MainRouter, App>(), FriendDetailsView {

    companion object {
        val fragmentTag = AppPresenterManager.FRIEND_DETAILS_TAG
        fun newInstance(): FriendDetailsFragment = FriendDetailsFragment()
    }


    override val presenterTag = AppPresenterManager.FRIEND_LIST_TAG
    override val layoutId = R.layout.fragment_friends_list

    private val imageLoadManager: ImageLoadManager = ImageLoadManager()
    private val bitmapTransformer: BitmapTransformer = CircularBitmapTransformer()
    override fun loadPhoto(url: String) {
        imageLoadManager.loadImageIntoImageView(
                iv_photo,
                bitmapTransformer,
                url)
    }

    override fun setupViews(view: View): View {
        return view
    }

    override fun preInit() {
        presenterManager = app.presenterManager
    }

    override fun postInit() {
    }

    override fun showProgressbar() {
        progressbar.show()
    }

    override fun hideProgressbar() {
        progressbar.hide()
    }

    fun showError(errorId: Int) {
        Toast.makeText(activity, getString(errorId), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        imageLoadManager.clear()
    }
}

