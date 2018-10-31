package org.books.services;

public enum Report {

    BOOKS("books.jrxml"),
    BOXES("boxes.jrml");

    private final String file;

    private static final String REPORT_DIR = "";

    Report(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}
