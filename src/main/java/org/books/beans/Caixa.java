package org.books.beans;

import java.io.InputStream;

public class Caixa {

    private Long id;

    private String titulo;

    private String descricao;

    private InputStream qrcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public InputStream getQrcode() {
        return qrcode;
    }

    public void setQrcode(InputStream qrcode) {
        this.qrcode = qrcode;
    }

    @Override public String toString() {
        return new StringBuilder("Caixa{")
            .append("id=").append(id)
            .append(", titulo='").append(titulo).append('\'')
            .append(", descricao='").append(descricao).append('\'')
            .append('}')
            .toString();
    }
}
