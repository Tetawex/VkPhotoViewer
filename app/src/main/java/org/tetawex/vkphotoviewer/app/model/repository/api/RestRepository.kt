package org.tetawex.vkphotoviewer.app.model.repository.api

import android.util.Log
import io.reactivex.Single
import org.json.JSONObject
import org.tetawex.vkphotoviewer.app.model.repository.AuthTokenProvider
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendDetails
import org.tetawex.vkphotoviewer.app.model.repository.api.dto.FriendsListItem
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by tetawex on 17.07.2018.
 */
class RestRepository(private val authTokenProvider: AuthTokenProvider,
                     private val webRequestPerformer: WebRequestPerformer) : Repository {

    override fun getFriendsFeed(offset: Int, count: Int): Single<List<FriendsListItem>> =
            Single.fromCallable {
                //Fetch
                val result = webRequestPerformer.performWebRequest(
                        URL("https://api.vk.com/method/friends.get?" +
                                "access_token=" + authTokenProvider.getAuthToken() +
                                "&fields=photo_100" +
                                "&offset=" + offset +
                                "&v=" + Config.API_VERSION +
                                "&count=" + count))

                //Log.d("result", result)

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

    override fun getFriendDetailsById(id: Long): Single<FriendDetails> =
            Single.fromCallable {
                val result = webRequestPerformer.performWebRequest(
                        URL("https://api.vk.com/method/users.get?" +
                                "access_token=" + authTokenProvider.getAuthToken() +
                                "&user_ids=" + id +
                                "&fields=about,photo_max_orig" +
                                "&v=" + Config.API_VERSION +
                                "&count=" + 1))
                Log.e("result for id " + id, result)

                //Deserialize
                val array = JSONObject(result).getJSONArray("response")

                if (array.length() < 1)
                    throw UserDoesNotExistException()

                Log.e("jsonObj ", "" + array.length() + "  " + array.getJSONObject(0).toString())

                val jsonObject = array.getJSONObject(0)
                val fullName = jsonObject.getString("first_name") + " " + jsonObject.getString("last_name")
                val photoUrl = jsonObject.getString("photo_max_orig")

                var about = ""
                if (jsonObject.has("about"))
                    about = jsonObject.getString("about")

                return@fromCallable FriendDetails(fullName, photoUrl, about)
            }

}