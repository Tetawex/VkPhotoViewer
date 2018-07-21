package org.tetawex.vkphotoviewer.app.view.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import kotlinx.android.synthetic.main.view_progressbar.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.app.App
import org.tetawex.vkphotoviewer.app.presenter.AppPresenterManager
import org.tetawex.vkphotoviewer.app.presenter.MainPresenter
import org.tetawex.vkphotoviewer.app.view.abs.MainView
import org.tetawex.vkphotoviewer.app.view.router.MainRouter
import org.tetawex.vkphotoviewer.base.BaseActivity
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
        fragmentManager = supportFragmentManager!!
        presenterManager = app.presenterManager
    }

    override fun postInit() {

    }

    override fun preInit() {
    }

    //View methods
    override fun openDefaultScreen() {
        navigateToFriendListScreen()
        clearBackStack()
        hideProgressbar()
    }

    override fun openLoginScreen() {

        navigateToLoginScreen()
    }

    //Router methods
    override fun navigateToLoginScreen() {
        Log.d("nav", "login")
        replaceFragment(LoginFragment.fragmentTag, addToBackStack = false, clearBackStack = true)
    }

    override fun goBack() {
        if (fragmentManager.backStackEntryCount > 1)
            fragmentManager.popBackStack()
    }

    override fun navigateToFriendListScreen() {
        Log.d("nav", "friends")
        replaceFragment(FriendListFragment.fragmentTag)
    }

    override fun navigateToFriendDetailsScreen() {
        Log.d("nav", "detail")
        replaceFragment(FriendDetailsFragment.fragmentTag)
    }

    override fun onBackPressed() {
        goBack()
    }

    private fun clearBackStack() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun replaceFragment(fragmentTag: String,
                                addToBackStack: Boolean = true,
                                clearBackStack: Boolean = false) {
        if (clearBackStack)
            clearBackStack()

        var existingFragment: Fragment? = fragmentManager.findFragmentByTag(fragmentTag)
        val transaction = fragmentManager.beginTransaction()

        //If fragment exists in backstack, put it in front
        if (existingFragment != null) {
            transaction.replace(R.id.fragment_placeholder, existingFragment, fragmentTag)
            //fragmentManager.popBackStack(fragmentTag, 0)
        }

        //Create it otherwise
        else {
            existingFragment = createFragmentByTag(fragmentTag)
            transaction.replace(R.id.fragment_placeholder, existingFragment, fragmentTag)
            if (addToBackStack)
                transaction.addToBackStack(fragmentTag)
            //fragmentManager.popBackStack(fragmentTag, 0)
        }

        currentFragmentTag = fragmentTag

        //Commit the transaction
        transaction.commit()
    }

    private fun createFragmentByTag(tag: String): Fragment {
        return when (tag) {
            LoginFragment.fragmentTag -> LoginFragment.newInstance()
            FriendListFragment.fragmentTag -> FriendListFragment.newInstance()
            FriendDetailsFragment.fragmentTag -> FriendDetailsFragment.newInstance()
            else -> LoginFragment.newInstance()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        //Turns out it's done automatically
        //fragmentManager.fragments.forEach { fragment ->
        //    fragmentManager.saveFragmentInstanceState(fragment)
        //}
    }
}