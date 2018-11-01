package org.books.beans;

import java.io.InputStream;

public class Obra {

    private Long id;

    private String titulo;

    private TipoObra tipo;

    private String tipoObra;

    private String barcode;

    private InputStream code;

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

    public TipoObra getTipo() {
        return tipo;
    }

    public void setTipo(TipoObra tipo) {
        this.tipo = tipo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public InputStream getCode() {
        return code;
    }

    public void setCode(InputStream code) {
        this.code = code;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Obra{");
        sb.append("id=").append(id);
        sb.append(", titulo='").append(titulo).append('\'');
        sb.append(", tipo='").append(tipo.name()).append('\'');
        sb.append(", barcode='").append(barcode).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getTipoObra() {
        return tipoObra;
    }

    public void setTipoObra(String tipoObra) {
        this.tipoObra = tipoObra;
    }
}
