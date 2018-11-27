package org.books.services

import com.google.common.io.BaseEncoding
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.books.entities.Owner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter
import java.security.Key
import java.util.Date
import java.util.logging.Logger

@Service
class SecurityService {

    @Autowired
    private val environment: Environment? = null

    fun gerarToken(owner: Owner): String {
        val apikeybytes = environment!!.getProperty(KEY_PROPERTY)?.toByteArray()
        val signingKey = SecretKeySpec(apikeybytes, SignatureAlgorithm.HS256.jcaName)

        return Jwts
                .builder()
                .setIssuer("My Library App")
                .setSubject("Save your library, find your stuff!")
                .setIssuedAt(Date())
                .claim("name", owner.name)
                .claim("password", owner.password)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact()
    }

    fun verificar(name: String, password: String, token: String): Boolean {
        val body = Jwts
                .parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(environment!!.getProperty(KEY_PROPERTY)))
                .parseClaimsJws(token)
                .body

        val nameToken = body["name"].toString()
        val passwordToken = body["password"].toString()

        return checkValues(name, nameToken) && checkValues(password, passwordToken)
    }

    private fun decode(encodedString: String): String {
        val decoded = BaseEncoding.base64().decode(encodedString)
        return String(decoded)
    }

    private fun checkValues(value1: String, value2: String): Boolean {
        return value1 == value2
    }

    companion object {

        private val KEY_PROPERTY = "secret.key"

        private val LOGGER = Logger.getLogger(SecurityService::class.java.name)
    }


}
