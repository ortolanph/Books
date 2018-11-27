package org.books.services

enum class Report private constructor(private val file: String) {

    WORKS("works.jrxml"),
    BOXES("boxes.jrxml"),
    LIBRARY("library.jrxml");

    val resourceName: String
        get() = String.format("%s%s", CLASSPATH, file)

    companion object {

        private val CLASSPATH = "classpath:reports/"
    }
}
