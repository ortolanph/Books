package org.books.interceptors

import org.books.persistence.OwnerRepository
import org.books.services.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationInterceptor : HandlerInterceptorAdapter() {

    @Autowired
    private val securityService : SecurityService? = null

    @Autowired
    private val ownerRepository : OwnerRepository? = null

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val authorization = request!!.getHeader(AUTHORIZATION_HEADER)

        val infoToken = securityService!!.recuperarInformacoesToken(authorization)

        val owner = ownerRepository!!.findById(infoToken["id"].toString().toInt())



        LOGGER.info("Gotcha!")

        return true
    }

    companion object {

        private val LOGGER = Logger.getLogger(AuthorizationInterceptor::class.java!!.name)

        private val AUTHORIZATION_HEADER = "Authorization"
    }
}
