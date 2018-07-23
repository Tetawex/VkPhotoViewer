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

    companion object {
        const val stubAuthToken = "token"
        const val stubFirstName = "first"
        const val stubSecondName = "second"
        const val stubUserId = 0L
        const val stubPhotoUrl = "photo_url"
        const val stubAbout = "about"
    }

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
}