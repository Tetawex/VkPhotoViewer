package org.tetawex.vkphotoviewer.app.view.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.view_progressbar.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.presenter.MainPresenter
import org.tetawex.vkphotoviewer.app.view.abs.MainView
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.base.BaseActivity
import org.tetawex.vkphotoviewer.base.PresenterManager
import org.tetawex.vkphotoviewer.base.util.viewextensions.hide
import org.tetawex.vkphotoviewer.base.util.viewextensions.show

/**
 * Created by tetawex on 18.07.2018.
 */
class MainActivity : BaseActivity<MainView, MainPresenter, App>(), MainView, MainRouter {

    override val layoutId: Int = R.layout.activity_main
    override val presenterTag: String = AppPresenterManager.MAIN_TAG

    private lateinit var fragmentManager: FragmentManager

    private var currentFragmentTag = ""

    override fun showProgressbar() {
        progressbar.show()
    }

    override fun hideProgressbar() {
        progressbar.hide()
    }

    override fun setupViews() {
    }

    override fun postInit() {
    }

    override fun preInit() {
        fragmentManager = supportFragmentManager!!
        presenterManager = app.presenterManager
        app.mainRouter = this
    }

    override fun goBack() {
        if (currentFragmentTag == LoginFragment.fragmentTag)
            return

        fragmentManager.popBackStack()
    }

    override fun navigateToLoginScreen() {
        Log.d("regoto", "login")
        replaceFragment(LoginFragment.fragmentTag, clearBackStack = true)
    }

    override fun navigateToFriendsListScreen() {
        Log.d("regoto", "friends")
        replaceFragment(FriendsListFragment.fragmentTag)
    }

    override fun navigateToFriendsDetailScreen() {
        Log.d("regoto", "detail")
        replaceFragment(FriendsDetailFragment.fragmentTag)
    }

    override fun onBackPressed() {
        goBack()
    }

    private fun replaceFragment(fragmentTag: String,
            //addToBackStack: Boolean = true,
                                clearBackStack: Boolean = false) {
        val existingFragment: Fragment? = fragmentManager.findFragmentByTag(fragmentTag)
        val transaction = fragmentManager.beginTransaction()

        //If fragment exists in backstack, put it in front
        if (existingFragment != null) {
            transaction.replace(R.id.fragment_placeholder, existingFragment, fragmentTag)
            //fragmentManager.popBackStack(fragmentTag, 0)
        }

        //Create it otherwise
        else {
            val newFragment = createFragmentByTag(fragmentTag)
            transaction.replace(R.id.fragment_placeholder, newFragment, fragmentTag)
            transaction.addToBackStack(fragmentTag)
            //fragmentManager.popBackStack(fragmentTag, 0)
        }

        //if (clearBackStack)
        //fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        currentFragmentTag = fragmentTag

        //Commit the transaction
        transaction.commit()
    }

    private fun createFragmentByTag(tag: String): Fragment {
        return when (tag) {
            LoginFragment.fragmentTag -> LoginFragment.newInstance()
            FriendsListFragment.fragmentTag -> FriendsListFragment.newInstance()
            FriendsDetailFragment.fragmentTag -> FriendsDetailFragment.newInstance()
            else -> LoginFragment.newInstance()
        }
    }
}