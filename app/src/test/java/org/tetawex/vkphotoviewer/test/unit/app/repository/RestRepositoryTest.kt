package org.tetawex.vkphotoviewer.test.unit.app.repository

import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.whenever
import org.json.JSONArray
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.tetawex.vkphotoviewer.app.model.repository.AuthTokenProvider
import org.tetawex.vkphotoviewer.app.model.repository.api.RestRepository
import org.tetawex.vkphotoviewer.app.model.repository.api.WebRequestPerformer
import org.tetawex.vkphotoviewer.test.unit.rule.RuledTest
import java.net.URL

@RunWith(MockitoJUnitRunner::class)
class RestRepositoryTest : RuledTest() {
    @Mock
    lateinit var webRequestPerformer: WebRequestPerformer
    @Mock
    lateinit var authTokenProvider: AuthTokenProvider


    val stubAuthToken = "token"
    val stubFirstName = "first"
    val stubSecondName = "second"
    val stubUserId = 0L
    val stubPhotoUrl = "photo_url"
    val stubAbout = "about"
    val stubCount = 10000


    @Before
    fun init() {
        whenever(authTokenProvider.getAuthToken()).thenReturn(stubAuthToken)
    }

    @After
    fun dispose() {
    }

    @Test
    fun restRepository_getFriendDetails_deserializeOnSuccessJsonCorrectly() {
        whenever(webRequestPerformer
                .performWebRequest(anyOrNull<URL>()))
                .thenReturn(createFriendDetailsResponse(true))

        val restRepository = RestRepository(authTokenProvider, webRequestPerformer)
        restRepository.getFriendDetailsById(stubUserId)
                .test()
                .assertNoErrors()
                .assertValue { details ->
                    assertEquals(stubFirstName + " " + stubSecondName, details.fullName)
                    assertEquals(stubPhotoUrl, details.photoUrl)
                    assertEquals(stubAbout, details.about)

                    return@assertValue true
                }
    }

    @Test
    fun restRepository_getFriendDetails_deserializeOnSuccessJsonWithoutAboutFieldCorrectly() {
        whenever(webRequestPerformer
                .performWebRequest(anyOrNull<URL>()))
                .thenReturn(createFriendDetailsResponse(false))

        val restRepository = RestRepository(authTokenProvider, webRequestPerformer)
        restRepository.getFriendDetailsById(stubUserId)
                .test()
                .assertNoErrors()
                .assertValue { details ->
                    assertEquals(stubFirstName + " " + stubSecondName, details.fullName)
                    assertEquals(stubPhotoUrl, details.photoUrl)
                    assertEquals("", details.about)

                    return@assertValue true
                }
    }

    @Test
    fun restRepository_getFriendDetails_throwExceptionOnMalformedResponse() {
        whenever(webRequestPerformer
                .performWebRequest(anyOrNull<URL>()))
                .thenReturn("abc")

        val restRepository = RestRepository(authTokenProvider, webRequestPerformer)
        restRepository.getFriendDetailsById(stubUserId)
                .test()
                .assertFailure(Throwable::class.java)
    }

    @Test
    fun restRepository_getFriendList_deserializeOnSuccessJsonCorrectly() {
        whenever(webRequestPerformer
                .performWebRequest(anyOrNull<URL>()))
                .thenReturn(createFriendListResponse())

        val restRepository = RestRepository(authTokenProvider, webRequestPerformer)
        restRepository.getFriendList(0, stubCount)
                .test()
                .assertNoErrors()
                .assertValue { list ->
                    assertEquals(stubCount, list.size)
                    list.forEach {
                        assertEquals(stubFirstName + " " + stubSecondName, it.fullName)
                        assertEquals(stubPhotoUrl, it.photoUrl)
                        assertEquals(stubUserId, it.id)
                    }

                    return@assertValue true
                }
    }

    @Test
    fun restRepository_getFriendList_throwExceptionOnMalformedResponse() {
        whenever(webRequestPerformer
                .performWebRequest(anyOrNull<URL>()))
                .thenReturn("abc")

        val restRepository = RestRepository(authTokenProvider, webRequestPerformer)
        restRepository.getFriendDetailsById(stubUserId)
                .test()
                .assertFailure(Throwable::class.java)
    }

    private fun createFriendDetailsResponse(includeAboutUserField: Boolean): String {
        val user = JSONObject()
        user.put("id", stubUserId)
        user.put("first_name", stubFirstName)
        user.put("last_name", stubSecondName)
        user.put("photo_max_orig", stubPhotoUrl)
        if (includeAboutUserField)
            user.put("about", stubAbout)

        val response = JSONArray()
        response.put(0, user)

        val jsonObject = JSONObject()
        jsonObject.put("response", response)

        return jsonObject.toString()
    }

    private fun createFriendListResponse(): String {
        val items = JSONArray()
        for (i in 0 until stubCount) {
            val user = JSONObject()
            user.put("id", stubUserId)
            user.put("first_name", stubFirstName)
            user.put("last_name", stubSecondName)
            user.put("photo_100", stubPhotoUrl)
            items.put(i, user)
        }

        val response = JSONObject()
        response.put("items", items)
        response.put("count", stubCount)

        val jsonObject = JSONObject()
        jsonObject.put("response", response)

        return jsonObject.toString()
    }
}