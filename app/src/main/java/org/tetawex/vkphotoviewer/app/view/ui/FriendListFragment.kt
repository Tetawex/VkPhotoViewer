package org.tetawex.vkphotoviewer.app.view.ui

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
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
        const val fragmentTag = AppPresenterManager.FRIEND_LIST_TAG
        const val FRIEND_LIST_LM_PARCELABLE_TAG = "friend_list_lv_parc_tag"
        fun newInstance(): FriendListFragment = FriendListFragment()
    }


    override val presenterTag = AppPresenterManager.FRIEND_LIST_TAG
    override val layoutId = R.layout.fragment_friends_list

    private val imageLoadManager: ImageLoadManager = ImageLoadManager()

    private lateinit var friendListRecyclerAdapter: FriendListRecyclerAdapter
    private lateinit var friendsListLayoutManager: LinearLayoutManager
    private lateinit var recyclerViewState: Parcelable

    var counter = 0
    override fun appendList(items: List<FriendsListItem>) {
        Log.e("tag " + counter, items[0].fullName)

        friendListRecyclerAdapter.appendDataWithNotify(items)

        counter++
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

        rv_friend_list.adapter = friendListRecyclerAdapter
        friendsListLayoutManager.onRestoreInstanceState(recyclerViewState)
        rv_friend_list.layoutManager = friendsListLayoutManager
    }

    override fun postInit() {

    }

    override fun showProgressbar() {
        progressbar.show()
    }

    override fun hideProgressbar() {
        progressbar.hide()
    }

    override fun onStop() {
        recyclerViewState = rv_friend_list.layoutManager.onSaveInstanceState()
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        friendsListLayoutManager = LinearLayoutManager(activity)
        recyclerViewState = friendsListLayoutManager.onSaveInstanceState()
        friendListRecyclerAdapter = FriendListRecyclerAdapter(
                context!!,
                imageLoadManager,
                { item -> presenter.onOpenFriendDetails(item) }
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(FRIEND_LIST_LM_PARCELABLE_TAG, recyclerViewState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.getParcelable<LinearLayoutManager.SavedState>(FRIEND_LIST_LM_PARCELABLE_TAG)?.also {
            recyclerViewState = it
        }
        Log.e("fr acreated", "dddd")
    }

    fun showError(errorId: Int) {
        Toast.makeText(activity, getString(errorId), Toast.LENGTH_SHORT).show()
    }
}
