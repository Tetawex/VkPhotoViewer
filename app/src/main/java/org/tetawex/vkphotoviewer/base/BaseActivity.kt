package org.tetawex.vkphotoviewer.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import org.tetawex.vkphotoviewer.R

import java.io.IOException

/**
 * Created by tetawex on 27.02.2018.
 */

abstract class BaseActivity<V : BaseView, out P : BasePresenter<V>, A> : AppCompatActivity(), BaseView {

    abstract val layoutId: Int

    abstract val presenterTag: String

    abstract val presenterManager: PresenterManager

    private var _presenter: P? = null
    val presenter: P
        get() {
            //TODO do something about this, I dunno...
            if (_presenter == null)
                attachPresenter()
            return _presenter!!
        }

    @Suppress("UNCHECKED_CAST")
    val app: A
        get() {
            return application as A
        }

    private var firstAttach = true

    abstract fun setupViews()

    abstract fun postInit()
    abstract fun preInit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preInit()

        setContentView(layoutId)
        setupViews()

        if (savedInstanceState != null) {
            firstAttach = false
        }

        attachPresenter()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (firstAttach) {
            firstAttach = false
            _presenter!!.onFirstViewAttached()
        }
        postInit()
    }

    override fun onStop() {
        super.onStop()
        detachPresenter()
    }


    private fun attachPresenter() {
        try {
            //Crashes if presenter does not match...
            _presenter = presenterManager.getPresenter<P>(presenterTag)
            _presenter!!.onViewAttached(this as V)
        } catch (cce: ClassCastException) {
            cce.printStackTrace()
            showToast(R.string.err_presenter)
        }

    }

    protected fun detachPresenter() {
        _presenter = null
    }

    //Removes presenter from the map
    override fun dispose() {
        presenterManager.disposePresenter(presenterTag)
    }

    override fun showError(t: Throwable) {
        if (t is IOException)
            showToast(R.string.err_no_internet)
        else
            showToast(R.string.err_generic)
    }

    fun showToast(stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
    }
}