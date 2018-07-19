package org.tetawex.vkphotoviewer.base.oauth2.account_manager_discarded

import android.app.Service
import android.content.Intent
import android.os.IBinder


/**
 * Created by tetawex on 17.07.2018.
 */
@Deprecated("")
abstract class AbstractAccountAuthenticatorService : Service() {
    // Instance field that stores the authenticator object
    // Notice, this is the same Authenticator class we defined earlier
    lateinit var authenticator: Authenticator
    abstract val authenticatorLoginInteractor: AuthenticatorLoginInteractor


    override fun onCreate() {
        // Create a new authenticator object
        authenticator = Authenticator(this, authenticatorLoginInteractor)
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    override fun onBind(intent: Intent): IBinder {
        return authenticator.iBinder
    }
}