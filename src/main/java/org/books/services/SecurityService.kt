package org.books.services

import com.google.common.io.BaseEncoding
import io.jsonwebtoken.*
import org.books.entities.Owner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter
import java.util.Date

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
            .setSigningKey(apiKeyBytes())
            .parseClaimsJws(token)
            .body


    private fun secretKey() : SecretKeySpec =
        SecretKeySpec(
            apiKeyBytes(),
            SignatureAlgorithm.HS256.jcaName)

    private fun apiKeyBytes() : ByteArray =
        DatatypeConverter
            .parseBase64Binary(
                environment!!.getProperty(KEY_PROPERTY)
            )

    fun checkValues(value1: String?, value2: String?): Boolean = value1.equals(value2)

    fun checkValues(value1: Int?, value2: Int?) : Boolean = value1 == value2

    fun decode(encodedString: String): String = String(BaseEncoding.base64().decode(encodedString))

    companion object {
        private val KEY_PROPERTY = "secret.key"
    }


}
