package org.tetawex.vkphotoviewer.app.model.repository.api

import android.util.Log
import io.reactivex.Single
import org.json.JSONObject
import org.tetawex.vkphotoviewer.app.model.repository.AuthTokenProvider
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetail
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by tetawex on 17.07.2018.
 */
class RestRepository(private val authTokenProvider: AuthTokenProvider) : Repository {
    private fun performWebRequest(url: URL): String {
        val urlConnection = url.openConnection() as HttpURLConnection
        val bufferedInputStream = BufferedInputStream(urlConnection.inputStream)
        val scanner = Scanner(bufferedInputStream).useDelimiter("\\A")

        urlConnection.disconnect()
        return if (scanner.hasNext()) scanner.next() else ""
    }

    override fun getFriendsFeed(offset: Int, count: Int): Single<List<FriendsListItem>> {
        return Single.fromCallable {
            //Fetch

            val result = performWebRequest(
                    URL("https://api.vk.com/method/friends.get?" +
                            "access_token=" + authTokenProvider.getAuthToken() +
                            "&fields=fullName,photo_100" +
                            "&offset=" + offset +
                            "&v=" + Config.API_VERSION +
                            "&count=" + count))

            //Log.e("result",result)

            //Deserialize
            val response = JSONObject(result).getJSONObject("response")

            val array = response.getJSONArray("items")
            val length = array.length()

            val friendList: MutableList<FriendsListItem> = ArrayList(length)

            for (i in 0 until length) {

                val jsonObject = array.getJSONObject(i)
                val id = jsonObject.getLong("id")
                val fullName = jsonObject.getString("first_name") + " " + jsonObject.getString("last_name")
                val photoUrl = jsonObject.getString("photo_100")//get smaller photo

                friendList.add(FriendsListItem(
                        id = id,
                        fullName = fullName,
                        photoUrl = photoUrl))
            }
            return@fromCallable friendList
        }
    }

    override fun getFriendDetailsById(id: Long): Single<FriendDetail> {
        val result = performWebRequest(
                URL("https://api.vk.com/method/users.get?" +
                        "access_token=" + authTokenProvider.getAuthToken() +
                        "&fields=fullName, about, photo_100" +
                        "&v=" + Config.API_VERSION +
                        "&count=" + 1))
        Log.e("result", result)
        return Single.just(FriendDetail("", "", ""))
    }

}