package org.tetawex.vkphotoviewer.app.view.ui

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_friends_list.*
import kotlinx.android.synthetic.main.view_progressbar.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.presenter.FriendListPresenter
import org.tetawex.vkphotoviewer.app.view.abs.FriendListView
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.base.RoutedFragment
import org.tetawex.vkphotoviewer.base.bitmap.legacy.ImageLoadManager
import org.tetawex.vkphotoviewer.base.util.viewextensions.hide
import org.tetawex.vkphotoviewer.base.util.viewextensions.show


class FriendListFragment : RoutedFragment<FriendListView, FriendListPresenter, MainRouter, App>(), FriendListView {
    override fun onGoneBackFromThisScreen() {
    }

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
    private var recyclerViewState: Parcelable? = null

    override fun appendList(items: List<FriendsListItem>) {
        Log.e("appended adapter is", friendListRecyclerAdapter.toString())

        Log.e("current thread is ", Thread.currentThread().name)
        friendListRecyclerAdapter.appendDataWithNotify(items)
    }

    override fun setList(items: List<FriendsListItem>) {
        friendListRecyclerAdapter.replaceDataWithNotify(items)
    }

    override fun clearList() {
        friendListRecyclerAdapter.clear()
    }

    override fun setupViews(view: View): View {
        friendListRecyclerAdapter = FriendListRecyclerAdapter(
                context!!,
                imageLoadManager,
                { item, position -> presenter.onOpenFriendDetails(item) })
        return view
    }

    override fun preInit() {
        presenterManager = app.presenterManager

        rv_friend_list.adapter = friendListRecyclerAdapter
        Log.e("adapter is", friendListRecyclerAdapter.toString())

        friendsListLayoutManager = LinearLayoutManager(activity)
        if (recyclerViewState != null)
            friendsListLayoutManager.onRestoreInstanceState(recyclerViewState)
        rv_friend_list.layoutManager = friendsListLayoutManager
    }

    override fun openFriendDetails(id: Long, fullName: String, photoPreviewUrl: String) {
        val transitionList: MutableList<View> = ArrayList(1)
        rv_friend_list
                .findViewWithTag<ImageView>(TransitionNames.TRANSITION_FRIEND_LIST_FRIEND_DETAILS + id)
                ?.also {
                    transitionList.add(it)
                }
        router.navigateToFriendDetailsScreen(id, fullName, photoPreviewUrl, transitionList)
    }

    override fun postInit() {
    }

    override fun showProgressbar() {
        progressbar.show()
    }

    override fun hideProgressbar() {
        progressbar.hide()
    }

    override fun onResume() {
        friendListRecyclerAdapter.notifyDataSetChanged()
        super.onResume()
        friendListRecyclerAdapter.notifyDataSetChanged()
        Log.e("the actual adapter is", rv_friend_list.adapter.toString())
    }

    override fun onStop() {
        super.onStop()
        recyclerViewState = rv_friend_list.layoutManager.onSaveInstanceState()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(FRIEND_LIST_LM_PARCELABLE_TAG, friendsListLayoutManager.onSaveInstanceState())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getParcelable<LinearLayoutManager.SavedState>(FRIEND_LIST_LM_PARCELABLE_TAG)?.also {
            Log.e("rv state restored", "dddd")
            recyclerViewState = it
        }
    }

    fun showError(errorId: Int) {
        Toast.makeText(activity, getString(errorId), Toast.LENGTH_SHORT).show()
    }
}

