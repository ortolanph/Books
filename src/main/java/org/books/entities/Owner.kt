package org.books.entities

import javax.persistence.*

@Entity
@Table(name = "owner")
class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column
    var name: String? = null

    @Column(name = "pass")
    var password: String? = null

    @Column
    var salt: String? = null
}
