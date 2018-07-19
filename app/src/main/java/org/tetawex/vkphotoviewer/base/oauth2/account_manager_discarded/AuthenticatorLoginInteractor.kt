package org.tetawex.vkphotoviewer.base.oauth2.account_manager_discarded

/**
 * Created by tetawex on 17.07.2018.
 */
@Deprecated("")
interface AuthenticatorLoginInteractor {
    fun login(name: String, password: String): String
}