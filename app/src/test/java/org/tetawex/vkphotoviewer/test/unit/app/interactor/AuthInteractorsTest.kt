package org.tetawex.vkphotoviewer.test.unit.app.interactor

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.tetawex.vkphotoviewer.app.model.usecase.AuthTokenInteractor
import org.tetawex.vkphotoviewer.app.model.usecase.LoginPostDataInteractor
import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.test.unit.rule.RuledTest

@RunWith(MockitoJUnitRunner::class)
class AuthInteractorsTest : RuledTest() {
    @Mock
    lateinit var preferences: Preferences
    @Mock
    lateinit var repository: Repository

    val stubAuthToken = "token"
    val stubUserId = "id"

    @Before
    fun init() {
        whenever(preferences.saveAuthToken(stubAuthToken)).thenReturn(preferences)
    }

    @After
    fun dispose() {
    }

    @Test
    fun authTokenUseCase_tokenReceived_tokenSavedToPreferences() {
        val authTokenUseCase = AuthTokenInteractor(repository, preferences)
        authTokenUseCase.saveAccessToken(stubAuthToken, stubUserId).test()

        verify(preferences).saveAuthToken(stubAuthToken)
        verify(preferences).commit()
    }

    @Test
    fun loginPostData_containsRequiredFields() {
        val loginPostDataInteractor = LoginPostDataInteractor()
        val data = loginPostDataInteractor.getPostDataUrl()
        assertTrue(data.contains("client_id=")
                && data.contains("&redirect_uri=")
                && data.contains("&v=")
                && data.contains("&scope=")
                && data.contains("&response_type=")
                && data.contains("display=")
        )
    }
}
