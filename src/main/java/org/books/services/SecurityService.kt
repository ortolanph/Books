package org.books.services

import com.google.common.io.BaseEncoding
import io.jsonwebtoken.*
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

    fun gerarToken(owner: Owner): String  =
        Jwts
            .builder()
            .setIssuer("My Library App")
            .setSubject("Save your library, find your stuff!")
            .setIssuedAt(Date())
            .claim("id", owner.id)
            .claim("name", owner.name)
            .claim("password", owner.password)
            .signWith(SignatureAlgorithm.HS256, secretKey())
            .compact()

    fun recuperarInformacoesToken(token: String) : Claims =
        Jwts
            .parser()
            .setSigningKey(secretKey())
            .parseClaimsJws(token)
            .body


    private fun secretKey() : SecretKeySpec =
        SecretKeySpec(
            DatatypeConverter
                .parseBase64Binary(
                        environment!!.getProperty(KEY_PROPERTY)
                ),
            SignatureAlgorithm.HS256.jcaName)


    private fun decode(encodedString: String): String =
        String(BaseEncoding.base64().decode(encodedString))

    private fun checkValues(value1: String, value2: String): Boolean =
        value1 == value2

    companion object {
        private val KEY_PROPERTY = "secret.key"
    }


}
