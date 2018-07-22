package org.tetawex.vkphotoviewer.test.app.usecase

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.tetawex.vkphotoviewer.app.model.interactor.AuthTokenUseCase
import org.tetawex.vkphotoviewer.app.model.interactor.LoginPostDataUseCase
import org.tetawex.vkphotoviewer.app.model.repository.Preferences
import org.tetawex.vkphotoviewer.app.model.repository.Repository
import org.tetawex.vkphotoviewer.test.rule.RuledTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://authenticator.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class AuthUseCasesTest : RuledTest() {
    @Mock
    lateinit var preferences: Preferences
    @Mock
    lateinit var repository: Repository

    val stubAuthToken = "stub"
    val stubUserId = "stub"

    @Before
    fun init() {
        whenever(preferences.saveAuthToken(stubAuthToken)).thenReturn(preferences)
    }

    @After
    fun dispose() {
    }

    @Test
    fun authTokenUseCase_tokenReceived_tokenSavedToPreferences() {
        val authTokenUseCase = AuthTokenUseCase(repository, preferences)
        authTokenUseCase.saveAccessToken(stubAuthToken, stubUserId).subscribe()

        verify(preferences).saveAuthToken(stubAuthToken)
        verify(preferences).commit()
    }

    @Test
    fun loginPostData_containsRequiredFields() {
        val loginPostDataInteractor = LoginPostDataUseCase()
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
