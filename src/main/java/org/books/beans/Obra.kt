package org.books.beans

import java.io.InputStream

class Obra {

    var id: Long? = null

    var titulo: String? = null

    var tipo: TipoObra? = null

    var tipoObra: String? = null

    var barcode: String? = null

    var code: InputStream? = null

    override fun toString(): String {
        return StringBuilder("Obra{")
            .append("id=").append(id)
            .append(", titulo='").append(titulo).append('\'')
            .append(", tipo='").append(tipo!!.name).append('\'')
            .append(", barcode='").append(barcode).append('\'')
            .append('}')
            .toString()
    }
}
