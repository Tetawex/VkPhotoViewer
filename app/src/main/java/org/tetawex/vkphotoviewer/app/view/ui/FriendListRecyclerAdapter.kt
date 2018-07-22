package org.tetawex.vkphotoviewer.app.view.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.recycleritem_friends_list.view.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.base.BaseRecyclerAdapter
import org.tetawex.vkphotoviewer.base.bitmap.ImageLoader
import org.tetawex.vkphotoviewer.base.bitmap.legacy.BitmapTransformer
import org.tetawex.vkphotoviewer.base.bitmap.legacy.BitmapTransformers
import org.tetawex.vkphotoviewer.base.bitmap.legacy.ImageLoadManager

/**
 * Created by tetawex on 20.07.2018.
 */
class FriendListRecyclerAdapter(context: Context,
                                private val imageLoadManager: ImageLoadManager,
                                val itemClickListener: (FriendsListItem, Int) -> Unit) :
        BaseRecyclerAdapter<FriendsListItem, FriendListRecyclerAdapter.ViewHolder>(context) {

    private val bitmapTransformer: BitmapTransformer = BitmapTransformers.CIRCULAR

    override val layoutId: Int = R.layout.recycleritem_friends_list

    override fun bindSingleItem(viewHolder: ViewHolder, item: FriendsListItem, position: Int) {
        viewHolder.view.run {
            iv_photo.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.shape_circle))
            ImageLoader.loadImageIntoView(iv_photo, item.photoUrl, BitmapTransformers.CIRCULAR)
            tv_name.text = item.fullName
            setOnClickListener { itemClickListener.invoke(item, position) }

            //Set iv's tag to find it later during shared element transition
            iv_photo.tag = TransitionNames.TRANSITION_FRIEND_LIST_FRIEND_DETAILS + item.id
            //Set shared element transition name
            ViewCompat.setTransitionName(iv_photo, TransitionNames.TRANSITION_FRIEND_LIST_FRIEND_DETAILS + item.id)
        }
    }

    override fun createVH(view: View): ViewHolder {
        return ViewHolder(view)
    }

    class ViewHolder(val view: View)
        : RecyclerView.ViewHolder(view) {

    }
}