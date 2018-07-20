package org.tetawex.vkphotoviewer.app.model.repository.api

import io.reactivex.Single
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsDetail
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
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
    override fun getFriendsFeed(offset: Int, count: Int): Single<List<FriendsListItem>> {
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
            val launchList = ArrayList<FriendsListItem>(count)

            val array = JSONArray(result)
            val length = array.length()
            for (i in 0 until length) {

                val jsonObject = array.getJSONObject(i)
                val launchDate = jsonObject.getLong("launch_date_unix")
                val details = jsonObject.getString("details")

                val rocketObj = jsonObject.getJSONObject("rocket")
                val rocketName = rocketObj.getString("rocket_name")

                val linksObj = jsonObject.getJSONObject("links")
                val patchUrl = linksObj.getString("mission_patch")
                val articleUrl = linksObj.getString("article_link")

            }
            return@fromCallable launchList
        }
    }

    override fun getFriendDetailsById(id: Long): Single<FriendsDetail> {
        return Single.just(FriendsDetail("", "", ""))
    }

}