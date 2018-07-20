package org.tetawex.vkphotoviewer.app.view.viewstate

import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import org.tetawex.vkphotoviewer.app.view.abs.FriendsListView
import org.tetawex.vkphotoviewer.app.view.abs.LoginView
import org.tetawex.vkphotoviewer.base.ViewState
import org.tetawex.vkphotoviewer.base.ViewWithProgressBarViewState

/**
 * Created by tetawex on 16.07.2018.
 */
class FriendsListViewState : ViewWithProgressBarViewState<FriendsListView>(), FriendsListView {
    //TODO add list-related command merging
    override fun appendList(items: List<FriendsListItem>) {
        submitCommand {
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