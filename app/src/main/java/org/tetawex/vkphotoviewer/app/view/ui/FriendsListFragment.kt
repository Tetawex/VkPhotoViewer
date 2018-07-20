package org.tetawex.vkphotoviewer.app.view.ui

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.view_progressbar.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.presenter.FriendsListPresenter
import org.tetawex.vkphotoviewer.app.presenter.LoginPresenter
import org.tetawex.vkphotoviewer.app.view.abs.FriendsListView
import org.tetawex.vkphotoviewer.app.view.abs.LoginView
import org.tetawex.vkphotoviewer.base.BaseFragment
import org.tetawex.vkphotoviewer.base.PresenterManager
import org.tetawex.vkphotoviewer.base.bitmap.ImageLoadManager


class FriendsListFragment : BaseFragment<FriendsListView, FriendsListPresenter, App>(), FriendsListView {

    companion object {
        val fragmentTag = AppPresenterManager.FRIENDS_LIST_TAG
        fun newInstance(): FriendsListFragment = FriendsListFragment()
    }


    override val presenterTag = AppPresenterManager.FRIENDS_LIST_TAG
    override val presenterManager: PresenterManager = app.presenterManager
    override val layoutId = R.layout.fragment_friends_list

    private val imageLoadManager: ImageLoadManager = ImageLoadManager()

    private lateinit var friendListRecyclerAdapter: FriendsListRecyclerAdapter

    override fun appendList(items: List<FriendsListItem>) {

    }

    override fun setList(items: List<FriendsListItem>) {
    }

    override fun clearList() {
    }

    override fun setupViews(view: View): View {
        friendListRecyclerAdapter = FriendsListRecyclerAdapter(
                context!!,
                imageLoadManager,
                { id -> presenter.openFriendsDetail(id) })

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
}

