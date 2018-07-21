package org.tetawex.vkphotoviewer.app.view.viewstate

import android.util.Log
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.app.view.abs.FriendListView
import org.tetawex.vkphotoviewer.base.ViewWithProgressBarViewState

/**
 * Created by tetawex on 16.07.2018.
 */
class FriendListViewState : ViewWithProgressBarViewState<FriendListView>(), FriendListView {
    override fun openFriendDetails(id: Long, fullName: String, photoPreviewUrl: String) {
        runCommand { it.openFriendDetails(id, fullName, photoPreviewUrl) }
    }

    //TODO add list-related command merging
    override fun appendList(items: List<FriendsListItem>) {
        submitCommand {
            Log.d("vs", "append count " + items.size)
            it.appendList(items)
        }
    }

    override fun setList(items: List<FriendsListItem>) {
        commandQueue.clear()
        submitCommand {
            it.setList(items)
        }
    }

    override fun clearList() {
        commandQueue.clear()
        submitCommand {
            it.clearList()
        }
    }
}