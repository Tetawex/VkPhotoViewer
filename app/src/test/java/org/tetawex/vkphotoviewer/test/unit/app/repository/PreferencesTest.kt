package org.tetawex.vkphotoviewer.test.unit.app.repository

import android.content.SharedPreferences
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.tetawex.vkphotoviewer.app.model.repository.PreferencesImpl
import org.tetawex.vkphotoviewer.test.unit.rule.RuledTest

@RunWith(MockitoJUnitRunner::class)
class PreferencesTest : RuledTest() {

    @Mock
    lateinit var sharedPreferences: SharedPreferences
    @Mock
    lateinit var editor: SharedPreferences.Editor

    val stubAuthToken = "token"

    @Before
    fun init() {
        whenever(editor.commit())
                .thenReturn(true)
        whenever(editor.putString(any(),any()))
                .thenReturn(editor)
        /*whenever(editor.putInt(any(),any()))
                .thenReturn(editor)
        whenever(editor.putBoolean(any(),any()))
                .thenReturn(editor)
        whenever(editor.putLong(any(),any()))
                .thenReturn(editor)*/

        whenever(sharedPreferences.edit())
                .thenReturn(editor)
        whenever(sharedPreferences.getString(PreferencesImpl.PREF_KEY_TOKEN, ""))
                .thenReturn(stubAuthToken)
    }
    @Test
    fun preferencesImpl_committedWhenEmpty_sharedPreferencesNotCommitted() {
        val preferencesImpl = PreferencesImpl(sharedPreferences)
        preferencesImpl.commit()
        verify(editor, never()).commit()
    }
    @Test
    fun preferencesImpl_committedWhenNotEmpty_sharedPreferencesCommitted() {
        val preferencesImpl = PreferencesImpl(sharedPreferences)
        preferencesImpl.saveAuthToken(stubAuthToken)
        preferencesImpl.commit()
        verify(editor).commit()
    }
    @Test
    fun preferencesImpl_tokenRequested_tokenFromSharedPreferencesReturned() {
        val preferencesImpl = PreferencesImpl(sharedPreferences)
        assertEquals(stubAuthToken, preferencesImpl.getAuthToken())
    }
    @Test
    fun preferencesImpl_tokenSaved_tokenSavedToSharedPreferencesEditor() {
        val preferencesImpl = PreferencesImpl(sharedPreferences)
        preferencesImpl.saveAuthToken(stubAuthToken)
        verify(editor).putString(PreferencesImpl.PREF_KEY_TOKEN, stubAuthToken)
    }
}