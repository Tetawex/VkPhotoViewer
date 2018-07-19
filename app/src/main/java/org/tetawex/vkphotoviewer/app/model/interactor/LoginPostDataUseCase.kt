package org.tetawex.vkphotoviewer.app.model.interactor

import org.tetawex.vkphotoviewer.app.model.repository.Config
import java.net.URLEncoder

class LoginPostDataUseCase : LoginPostDataInteractor {
    override fun getPostDataUrl() = StringBuilder()
            .append("client_id=").append(URLEncoder.encode(Config.CLIENT_ID, Config.ENCODING))
            .append("&display=").append(URLEncoder.encode("page", Config.ENCODING))
            .append("&redirect_uri=").append(URLEncoder.encode(Config.REDIRECT_URI, Config.ENCODING))
            .append("&v=").append(URLEncoder.encode(Config.API_VERSION, Config.ENCODING))
            .append("&scope=").append(URLEncoder.encode(Config.MASK_FULL.toString(), Config.ENCODING))
            .append("&response_type=").append(URLEncoder.encode("token", Config.ENCODING))
            .toString()
}