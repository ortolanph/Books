package org.books.services

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.books.entities.Owner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

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

    fun encryptPassword(password: String?, key: String?) : String {
        val cipher = generateCipher(key, Cipher.ENCRYPT_MODE)
        return Base64.getEncoder().encodeToString(cipher.doFinal(password!!.toByteArray()))
    }

    fun decryptPassord(encryptedPassword: String?, key: String?) : String {
        val cipher = generateCipher(key, Cipher.DECRYPT_MODE)
        return String(cipher.doFinal(Base64.getDecoder().decode(encryptedPassword)))
    }

    private fun generateCipher(key: String?, mode: Int) : Cipher {
        val aesKey = SecretKeySpec(key!!.toByteArray(), CRYPT_ALGORITHM)
        val cipher = Cipher.getInstance(CRYPT_ALGORITHM)
        cipher.init(mode, aesKey)
        return cipher
    }

    companion object {
        private val KEY_PROPERTY = "secret.key"

        private val CRYPT_ALGORITHM = "AES/ECB/PKCS5Padding"
    }


}
