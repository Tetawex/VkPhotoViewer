package org.tetawex.vkphotoviewer.app

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.tetawex.vkphotoviewer.R
import org.tetawex.vkphotoviewer.base.BaseActivity
import org.tetawex.vkphotoviewer.base.PresenterManager
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity<LoginView, LoginPresenter>(), LoginView {
    override fun showProgressbar() {

    }

    override fun hideProgressBar() {
        Single.just("hw" + Thread.currentThread().name)
                .delay(2, TimeUnit.SECONDS)
                //.observeOn(Schedulers.io())
                .doOnSuccess { item -> item + " " + Thread.currentThread().name }
                .observeOn(Schedulers.computation())
                .map { item -> item + " " + Thread.currentThread().name }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.trampoline())
                .subscribe { item -> tv_hello_world.text = item }
    }

    override fun setupViews() {

    }

    override fun postInit() {
    }

    override val layoutId: Int = R.layout.activity_main
    override val presenterTag: String = AppPresenterManager.LOGIN_TAG
    override val presenterManager: PresenterManager = App.instance.presenterManager//Proper DI please? Anyone?
}
