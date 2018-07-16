package tetawex.org.vkphotoviewer.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import tetawex.org.vkphotoviewer.R
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}
