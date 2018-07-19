package org.tetawex.vkphotoviewer.app.model.repository.api

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetails
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsFeedItem
import android.util.Log
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.json.JSONArray
import org.tetawex.vkphotoviewer.app.model.repository.AuthTokenProvider
import org.tetawex.vkphotoviewer.app.model.repository.Config
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


/**
 * Created by tetawex on 17.07.2018.
 */
class RestRepository(private val authTokenProvider: AuthTokenProvider) : Repository {
    override fun getFriendsFeed(offset: Int, count: Int): Single<List<FriendsFeedItem>> {
        return Single.fromCallable {
            //Fetch
            val url = URL("https://api.vk.com/method/friends.get?" +
                    "auth_token=" + authTokenProvider.getAuthToken() +
                    "&fields=nickname,photo_100" +
                    "&offset=" + offset +
                    "&api_version=" + Config.API_VERSION +
                    "&count=" + count)
            val urlConnection = url.openConnection() as HttpURLConnection

            val bufferedInputStream = BufferedInputStream(urlConnection.inputStream)

            val scanner = Scanner(bufferedInputStream).useDelimiter("\\A")
            val result = if (scanner.hasNext()) scanner.next() else ""

            urlConnection.disconnect()

            //TODO: remove logging
            Completable
                    .fromCallable { Log.d("vkvk", result) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe()

            //Deserialize
            val launchList = ArrayList<FriendsFeedItem>(count)

            val array = JSONArray(result)
            val length = array.length()
            for (i in 0 until length) {

                val `object` = array.getJSONObject(i)
                val launchDate = `object`.getLong("launch_date_unix")
                val details = `object`.getString("details")

                val rocketObj = `object`.getJSONObject("rocket")
                val rocketName = rocketObj.getString("rocket_name")

                val linksObj = `object`.getJSONObject("links")
                val patchUrl = linksObj.getString("mission_patch")
                val articleUrl = linksObj.getString("article_link")

            }
            return@fromCallable launchList
        }
    }

    override fun getFriendDetailsById(id: Long): Single<FriendDetails> {
        return Single.just(FriendDetails("", "", ""))
    }

}