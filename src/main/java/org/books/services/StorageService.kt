package org.books.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service

import java.io.IOException
import java.io.InputStream

@Service
class StorageService {

    @Autowired
    private val resourceLoader: ResourceLoader? = null

    fun loadJrxmlFile(report: Report): InputStream? {
        return getResource(report.resourceName)
    }

    fun loadLogo(): InputStream? {
        return getResource("classpath:reports/logo.png")
    }

    private fun getResource(resourceName: String): InputStream? {
        try {
            return resourceLoader!!.getResource(resourceName).inputStream
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }
}
