package org.tetawex.vkphotoviewer.app.view.ui

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_friends_list.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.presenter.FriendListPresenter
import org.tetawex.vkphotoviewer.app.view.abs.FriendListView
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.base.RoutedFragment
import org.tetawex.vkphotoviewer.base.bitmap.ImageLoadManager
import org.tetawex.vkphotoviewer.base.util.viewextensions.hide
import org.tetawex.vkphotoviewer.base.util.viewextensions.show


class FriendListFragment : RoutedFragment<FriendListView, FriendListPresenter, MainRouter, App>(), FriendListView {

    companion object {
        val fragmentTag = AppPresenterManager.FRIEND_LIST_TAG
        fun newInstance(): FriendListFragment = FriendListFragment()
    }


    override val presenterTag = AppPresenterManager.FRIEND_LIST_TAG
    override val layoutId = R.layout.fragment_friends_list

    private val imageLoadManager: ImageLoadManager = ImageLoadManager()

    private lateinit var friendListRecyclerAdapter: FriendListRecyclerAdapter

    override fun appendList(items: List<FriendsListItem>) {
        friendListRecyclerAdapter.appendDataWithNotify(items)
    }

    override fun setList(items: List<FriendsListItem>) {
        friendListRecyclerAdapter.replaceDataWithNotify(items)
    }

    override fun clearList() {
        friendListRecyclerAdapter.clear()
    }

    override fun setupViews(view: View): View {
        return view
    }

    override fun preInit() {
        presenterManager = app.presenterManager
    }

    override fun postInit() {
        friendListRecyclerAdapter = FriendListRecyclerAdapter(
                context!!,
                imageLoadManager,
                { item -> presenter.onOpenFriendsDetail(item) }
        )

        rv_friend_list.adapter = friendListRecyclerAdapter
        rv_friend_list.layoutManager = LinearLayoutManager(activity)
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
}

