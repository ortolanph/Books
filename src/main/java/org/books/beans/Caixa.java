package org.books.beans;

public class Caixa {

    private String titulo;

    private String descricao;

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

    @Override public String toString() {
        return new StringBuilder("Caixa{")
            .append("titulo='").append(titulo).append('\'')
            .append(", descricao='").append(descricao).append('\'')
            .append('}').toString();
    }
}
