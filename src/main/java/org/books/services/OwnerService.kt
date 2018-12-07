package org.books.services

import org.apache.commons.lang3.RandomStringUtils
import org.books.beans.LoggedOwner
import org.books.entities.Owner
import org.books.persistence.OwnerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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

        owner.salt = RandomStringUtils.randomAlphanumeric(KEY_SIZE)
        owner.password = securityService!!.encryptPassword(owner.password, owner.salt)

        repository!!.save(owner)

        loggedOwner.id = owner.id
        loggedOwner.name = owner.name
        loggedOwner.token = securityService!!.gerarToken(owner)

        return loggedOwner
    }

    companion object {

        private val KEY_SIZE : Int = 16

    }
}
