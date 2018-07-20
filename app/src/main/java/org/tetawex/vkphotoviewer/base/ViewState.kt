package org.tetawex.vkphotoviewer.base

import java.util.LinkedList
import java.util.Queue

/**
 * Created by Tetawex on 15.02.2018.
 */

abstract class ViewState<V : BaseView> : BaseView {
    private var view: V? = null
    private val commandQueue: Queue<(V) -> Unit>

    init {
        commandQueue = LinkedList()
    }

    fun restoreState(view: V) {
        for (command in commandQueue) {
            command.invoke(view)
        }
    }

    fun detachView() {
        view = null
    }

    fun attachView(view: V) {
        this.view = view
    }

    //For cached commands (default behavior)
    fun submitCommand(command: (V) -> Unit) {
        saveCommand(command)
        runCommand(command)
    }

    //For 1-time commands (like error toast)
    fun runCommand(command: (V) -> Unit) {
        view?.apply(command)
    }

    //For cached commands that only run when state is restored
    fun saveCommand(command: (V) -> Unit) {
        commandQueue.add(command)
    }

    //That's how an implemented ViewState would look like
    override fun showError(t: Throwable) {
        runCommand {
            it.showError(t)
        }
    }

    override fun dispose() {
        submitCommand {
            it.dispose()
        }
    }
    //--------
}