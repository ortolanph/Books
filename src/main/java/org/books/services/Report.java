package org.books.services;

public enum Report {

    WORKS("works.jrxml"),
    BOXES("boxes.jrxml"),
    LIBRARY("library.jrxml");

    private final String file;

    private static final String CLASSPATH = "classpath:reports/";

    Report(String file) {
        this.file = file;
    }

    public String getResourceName() {
        return String.format("%s%s", CLASSPATH, file);
    }
}
