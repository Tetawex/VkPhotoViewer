package org.tetawex.vkphotoviewer.base

import android.util.Log
import java.util.*

/**
 * Created by Tetawex on 15.02.2018.
 */

abstract class ViewState<V : BaseView> : BaseView {
    private var view: V? = null
    protected val commandQueue: Queue<(V) -> Unit>

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

    //For cached commands (DEFAULT behavior)
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
