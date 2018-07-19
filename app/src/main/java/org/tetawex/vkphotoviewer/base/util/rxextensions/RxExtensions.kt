package org.tetawex.vkphotoviewer.base.util.rxextensions

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by tetawex on 17.07.2018.
 * Edited by Tetawex on 23.07.2017.
 */

//Brand new extensions
fun Completable.applySchedulers(): Completable {
    return this
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.applySchedulers(): Single<T> {
    return this
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return this
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

//Legacy transformers for java peasants
fun applyCompletableSchedulers(): CompletableTransformer {
    return CompletableTransformer { tCompletable ->
        tCompletable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Single<T>.applySingleSchedulers(): SingleTransformer<T, T> {
    return SingleTransformer { tSingle ->
        tSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Observable<T>.applyObservableSchedulers(): ObservableTransformer<T, T> {
    return ObservableTransformer { tObservable ->
        tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> applySingleOpBeforeAndAfter(
        before: Runnable, after: Runnable): SingleTransformer<T, T> {
    return SingleTransformer { tSingle -> tSingle.doOnSuccess({ after.run() }).doOnSubscribe({ before.run() }) }
}

fun applyCompletableOpBeforeAndAfter(
        before: Runnable, after: Runnable): CompletableTransformer {
    return CompletableTransformer { tSingle -> tSingle.doOnTerminate({ after.run() }).doOnSubscribe({ before.run() }) }
}

fun <T> applyObservableOpBeforeAndAfter(
        before: Runnable, after: Runnable): ObservableTransformer<T, T> {
    return ObservableTransformer { tObservable -> tObservable.doOnTerminate({ after.run() }).doOnSubscribe({ t -> before.run() }) }
}

fun <T> applyObservableOpAfter(after: Runnable): ObservableTransformer<T, T> {
    return ObservableTransformer { tObservable -> tObservable.doOnComplete({ after.run() }) }
}

fun applyCompletableOpAfter(after: Runnable): CompletableTransformer {
    return CompletableTransformer { tObservable -> tObservable.doOnTerminate({ after.run() }) }
}

fun <T> applyDelayBefore(delay: Long = 200, timeUnit: TimeUnit = TimeUnit.MILLISECONDS): SingleTransformer<T, T> {
    return SingleTransformer { tSingle ->
        tSingle.delaySubscription(delay, timeUnit, AndroidSchedulers.mainThread())
    }
}
