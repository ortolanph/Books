package org.books.services;

public enum Report {

    WORKS("works.jrxml"),
    BOXES("boxes.jrxml"),
    LIBRARY("library.jrxml");

    private static final String CLASSPATH = "classpath:reports/";

    private final String file;

    Report(String file) {
        this.file = file;
    }

    public String getResourceName() {
        return String.format("%s%s", CLASSPATH, file);
    }
}
