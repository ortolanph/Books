package org.books.api

import org.books.beans.LoggedOwner
import org.books.entities.Owner
import org.books.services.OwnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/api/public", produces = arrayOf("application/json"))
class OwnerController {

    @Autowired
    private val ownerService: OwnerService? = null

    @PostMapping(value = "/register", consumes = arrayOf("application/json"))
    fun register(@RequestBody owner: Owner): ResponseEntity<LoggedOwner> {
        val possibleOwners = ownerService!!.procurarOwner(owner)

        return if (!possibleOwners.isEmpty()) {
            ResponseEntity
                    .badRequest()
                    .build()
        } else ResponseEntity
                .ok(ownerService.criarOwner(owner))

    }

}
