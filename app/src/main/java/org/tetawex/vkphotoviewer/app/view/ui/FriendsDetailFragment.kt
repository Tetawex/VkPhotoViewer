package org.tetawex.vkphotoviewer.app.view.ui

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_friends_detail.*
import kotlinx.android.synthetic.main.view_progressbar.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.presenter.FriendsDetailPresenter
import org.tetawex.vkphotoviewer.app.presenter.LoginPresenter
import org.tetawex.vkphotoviewer.app.view.abs.FriendsDetailView
import org.tetawex.vkphotoviewer.app.view.abs.LoginView
import org.tetawex.vkphotoviewer.base.BaseFragment
import org.tetawex.vkphotoviewer.base.PresenterManager
import org.tetawex.vkphotoviewer.base.bitmap.BitmapTransformer
import org.tetawex.vkphotoviewer.base.bitmap.CircularBitmapTransformer
import org.tetawex.vkphotoviewer.base.bitmap.ImageLoadManager


class FriendsDetailFragment : BaseFragment<FriendsDetailView, FriendsDetailPresenter, App>(), FriendsDetailView {

    companion object {
        val fragmentTag = AppPresenterManager.FRIENDS_LIST_TAG
        fun newInstance(): FriendsDetailFragment = FriendsDetailFragment()
    }


    override val presenterTag = AppPresenterManager.FRIENDS_LIST_TAG
    override val presenterManager: PresenterManager = app.presenterManager
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

    override fun onDestroy() {
        super.onDestroy()
        imageLoadManager.clear()
    }
}
