package org.tetawex.vkphotoviewer.base.oauth2.account_manager_discarded

import android.content.Context
import android.os.Bundle
import android.accounts.*
import android.content.Intent


/**
 * https://www.pilanites.com/android-account-manager/
 */
@Deprecated("")
class AccountAuthenticator(private val context: Context) : AbstractAccountAuthenticator(context) {

    override fun getAuthTokenLabel(authTokenType: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun confirmCredentials(response: AccountAuthenticatorResponse,
                                    account: Account, options: Bundle): Bundle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateCredentials(response: AccountAuthenticatorResponse,
                                   account: Account, authTokenType: String, options: Bundle): Bundle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthToken(response: AccountAuthenticatorResponse,
                              account: Account, authTokenType: String, options: Bundle): Bundle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasFeatures(response: AccountAuthenticatorResponse,
                             account: Account, features: Array<out String>): Bundle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editProperties(response: AccountAuthenticatorResponse, accountType: String): Bundle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAccount(response: AccountAuthenticatorResponse,
                            accountType: String, authTokenType: String,
                            requiredFeatures: Array<out String>, options: Bundle): Bundle {
        val intent = Intent(context, AccountAuthenticatorActivity::class.java)

        // This key can be anything. Try to use your domain/package
        intent.putExtra("org.tetawex.vkphotoviewer", accountType)

        // This key can be anything too. It's just a way of identifying the token's type (used when there are multiple permissions)
        intent.putExtra("full_access", authTokenType)

        // This key can be anything too. Used for your reference. Can skip it too.
        intent.putExtra("is_adding_new_account", true)

        // Copy this exactly from the line below.
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)

        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)

        return bundle
    }
}