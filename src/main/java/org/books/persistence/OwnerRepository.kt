package org.books.persistence

import org.books.entities.Owner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OwnerRepository : JpaRepository<Owner, Int> {

    fun findByName(name: String?): List<Owner>

}
