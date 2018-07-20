package org.tetawex.vkphotoviewer.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.tetawex.vkphotoviewer.R
import java.io.IOException

/**
 * Created by tetawex on 27.02.2018.
 */

abstract class BaseFragment<V : BaseView, out P : BasePresenter<V>, A> : Fragment(), BaseView {
    abstract val layoutId: Int
    abstract val presenterTag: String
    abstract val presenterManager: PresenterManager

    @Suppress("UNCHECKED_CAST")
    val app: A
        get() {
            return activity!!.application as A
        }

    private var _presenter: P? = null
    val presenter: P
        get() {
            //TODO do something about this, I dunno...
            if (_presenter == null)
                attachPresenter()
            return _presenter!!
        }

    private var firstAttach = true

    abstract fun setupViews(view: View): View

    abstract fun preInit()
    abstract fun postInit()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): android.view.View? {
        preInit()

        val view = inflater.inflate(layoutId, container, false)
        if (savedInstanceState != null) {
            firstAttach = false
        }

        attachPresenter()
        return setupViews(view)
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


    protected fun attachPresenter() {
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
        Toast.makeText(activity, stringId, Toast.LENGTH_SHORT).show()
    }
}
