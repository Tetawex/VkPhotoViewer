package org.tetawex.vkphotoviewer.app.view.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_friend_detail.*
import kotlinx.android.synthetic.main.view_progressbar.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.presenter.FriendDetailsPresenter
import org.tetawex.vkphotoviewer.app.view.abs.FriendDetailsView
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.base.RoutedFragment
import org.tetawex.vkphotoviewer.base.bitmap.BitmapTransformer
import org.tetawex.vkphotoviewer.base.bitmap.BitmapTransformers
import org.tetawex.vkphotoviewer.base.bitmap.ImageLoadManager
import org.tetawex.vkphotoviewer.base.util.viewextensions.hide
import org.tetawex.vkphotoviewer.base.util.viewextensions.show


class FriendDetailsFragment : RoutedFragment<FriendDetailsView, FriendDetailsPresenter, MainRouter, App>(), FriendDetailsView {
    override fun loadDefaultData() {
        arguments?.also {
            userId = it.getInt(BUNDLE_TAG_ID, 0).toLong()
            tv_full_name.text = it.getString(BUNDLE_TAG_FULL_NAME, "")
            imageLoadManager.loadImageIntoImageView(
                    iv_photo,
                    bitmapTransformer,
                    it.getString(BUNDLE_TAG_PHOTO_PREVIEW_URL, ""))
        }
    }

    companion object {
        const val fragmentTag = AppPresenterManager.FRIEND_DETAILS_TAG
        const val BUNDLE_TAG_ID = "id"
        const val BUNDLE_TAG_FULL_NAME = "full_name"
        const val BUNDLE_TAG_PHOTO_PREVIEW_URL = "photo_preview_url"
        fun newInstance(): FriendDetailsFragment = FriendDetailsFragment()
    }


    override val presenterTag = AppPresenterManager.FRIEND_DETAILS_TAG
    override val layoutId = R.layout.fragment_friend_detail

    private val imageLoadManager: ImageLoadManager = ImageLoadManager()
    private val bitmapTransformer: BitmapTransformer = BitmapTransformers.DEFAULT

    private var userId = 0L

    override fun loadPhoto(url: String) {
        imageLoadManager.loadImageIntoImageView(
                iv_photo,
                bitmapTransformer,
                url)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        //A window like this one should either be disposable or have a unique presenter tag
        //dispose()
    }
}

