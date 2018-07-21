package org.tetawex.vkphotoviewer.app.view.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.recycleritem_friends_list.view.*
import org.tetawex.vkphotoviewer.base.bitmap.BitmapTransformer
import org.tetawex.vkphotoviewer.base.bitmap.BitmapTransformers
import org.tetawex.vkphotoviewer.base.bitmap.ImageLoadManager

/**
 * Created by tetawex on 20.07.2018.
 */
class FriendListRecyclerAdapter(context: Context,
                                private val imageLoadManager: ImageLoadManager,
                                val itemClickListener: (FriendsListItem) -> Unit) :
        BaseRecyclerAdapter<FriendsListItem, FriendListRecyclerAdapter.ViewHolder>(context) {

    private val bitmapTransformer: BitmapTransformer = BitmapTransformers.CIRCULAR

    override val layoutId: Int = R.layout.recycleritem_friends_list

    override fun bindSingleItem(viewHolder: ViewHolder, item: FriendsListItem) {
        viewHolder.view.run {
            imageLoadManager.loadImageIntoImageView(iv_photo, bitmapTransformer, item.photoUrl)
            tv_name.text = item.nickname
            setOnClickListener { itemClickListener.invoke(item) }
        }
    }

    override fun createVH(view: View): ViewHolder {
        return ViewHolder(view)
    }

    class ViewHolder(val view: View)
        : RecyclerView.ViewHolder(view) {

    }
}