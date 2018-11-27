package org.books.beans

import java.io.InputStream

class Caixa {

    var id: Long? = null

    var titulo: String? = null

    var descricao: String? = null

    var qrcode: InputStream? = null

    override fun toString(): String {
        return StringBuilder("Caixa{")
                .append("id=").append(id)
                .append(", titulo='").append(titulo).append('\'')
                .append(", descricao='").append(descricao).append('\'')
                .append('}')
                .toString()
    }
}
