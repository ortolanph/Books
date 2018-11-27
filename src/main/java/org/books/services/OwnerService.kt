package org.books.services

import org.books.beans.LoggedOwner
import org.books.entities.Owner
import org.books.persistence.OwnerRepository
import org.olap4j.impl.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OwnerService {

    @Autowired
    private val repository: OwnerRepository? = null

    @Autowired
    private val securityService: SecurityService? = null

    fun procurarOwner(owner: Owner): List<Owner> {
        return repository!!.findByName(owner.name)
    }

    fun criarOwner(owner: Owner): LoggedOwner {
        val loggedOwner = LoggedOwner()

        owner.salt = UUID.randomUUID().toString()
        owner.password = encodedPassword(owner.password, owner.salt)

        repository!!.save(owner)

        loggedOwner.id = owner.id
        loggedOwner.name = owner.name
        loggedOwner.token = securityService!!.gerarToken(owner)

        return loggedOwner
    }

    private fun encodedPassword(password: String?, salt: String?): String {
        val passSalt = password + salt
        return Base64.encodeBytes(passSalt.toByteArray())
    }
}
