package org.tetawex.vkphotoviewer.app.view.ui

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_friend_detail.*
import kotlinx.android.synthetic.main.view_progressbar.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetails
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.presenter.FriendDetailsPresenter
import org.tetawex.vkphotoviewer.app.view.abs.FriendDetailsView
import org.tetawex.vkphotoviewer.app.view.abs.ImmersiveView
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.base.RoutedFragment
import org.tetawex.vkphotoviewer.base.bitmap.BitmapTransformer
import org.tetawex.vkphotoviewer.base.bitmap.BitmapTransformers
import org.tetawex.vkphotoviewer.base.bitmap.ImageLoader
import org.tetawex.vkphotoviewer.base.bitmap.legacy.ImageLoadManager
import org.tetawex.vkphotoviewer.base.util.viewextensions.hide
import org.tetawex.vkphotoviewer.base.util.viewextensions.show


class FriendDetailsFragment : RoutedFragment<FriendDetailsView, FriendDetailsPresenter, MainRouter, App>(), FriendDetailsView {
    override fun onGoneBackFromThisScreen() {
        Log.e("dispose", "on back")
        //A window like this one should either be disposable or have a unique presenter tag
        //But the mvp framework currently does not support tagging
        //So...
        dispose()
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
    private var fullName = ""
    private var imageUrl = ""


    override fun onStart() {
        arguments?.also {
            userId = it.getInt(BUNDLE_TAG_ID, 0).toLong()
            tv_full_name.text = it.getString(BUNDLE_TAG_FULL_NAME, "")
        }
        if (userId != 0L)
            ViewCompat.setTransitionName(iv_photo, TransitionNames.TRANSITION_FRIEND_LIST_FRIEND_DETAILS + userId)
        setImmersiveMode(true)
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
        setImmersiveMode(false)
    }

    private fun setImmersiveMode(enabled: Boolean) {
        if (activity is ImmersiveView)
            (activity as ImmersiveView).setUseImmersiveMode(enabled)
    }

    override fun setDetails(details: FriendDetails) {
        //Make empty to avoid double-loading
        imageUrl = details.photoUrl
        ImageLoader.loadImageIntoView(iv_photo, details.photoUrl)
        if (details.about.isNotEmpty())
            tv_about.text = details.about
        else
            tv_about.text = getString(R.string.user_about_empty)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.also {
            imageUrl = it.getString(BUNDLE_TAG_PHOTO_PREVIEW_URL, "")
        }
    }

    override fun setupViews(view: View): View {
        return view
    }

    override fun preInit() {
        presenterManager = app.presenterManager
        //Yes it's a band-aid as it violates mvp dumb-view rule, the variable should be assigned
        // via factory instead of being set by the view
        if (userId != 0L)
            presenter.userId = userId

        //It gets cancelled when presenter comes online
        if (imageUrl != "")
            ImageLoader.loadImageIntoView(
                    iv_photo,
                    imageUrl)
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

