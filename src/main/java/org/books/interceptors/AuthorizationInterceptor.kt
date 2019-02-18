package org.books.interceptors

import org.books.persistence.OwnerRepository
import org.books.services.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthorizationInterceptor : HandlerInterceptorAdapter() {

    @Autowired
    private val securityService: SecurityService? = null

    @Autowired
    private val ownerRepository: OwnerRepository? = null

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
//        val url = request.requestURL.toString()
//
//        if (!url.contains("public")) {
//            val authorization = request!!.getHeader(AUTHORIZATION_HEADER)
//
//            val infoToken = securityService!!.recuperarInformacoesToken(authorization)
//
//            val owner = ownerRepository!!.findById(infoToken["id"].toString().toInt()).get()
//
//            if (
//                    securityService.checkValues(owner.id, infoToken["id"].toString().toInt()) &&
//                    securityService.checkValues(owner.name, infoToken["name"].toString()) &&
//                    securityService.checkValues(infoToken["password"].toString(), owner.password.toString()) &&
//                    securityService.checkValues(
//                            securityService.decryptPassord(infoToken["password"].toString(), owner.salt),
//                            securityService.decryptPassord(owner.password, owner.salt))
//            ) {
//                request.setAttribute("LOGGED_USER_ID", owner.id)
//            } else {
//                response.status = 401
//                LOGGER.info("USUÁRIO NÃO ENCONTRADO")
//                return false
//            }
//        }

        return true
    }

    companion object {

        private val LOGGER = Logger.getLogger(AuthorizationInterceptor::class.java!!.name)

        private val AUTHORIZATION_HEADER = "Authorization"
    }
}
