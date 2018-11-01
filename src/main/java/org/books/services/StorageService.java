package org.books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class StorageService {

    @Autowired
    private ResourceLoader resourceLoader;

    public InputStream loadJrxmlFile(Report report) {
        return getResource(report.getResourceName());
    }

    public InputStream loadLogo() {
        return getResource("classpath:reports/logo.png");
    }

    private InputStream getResource(String resourceName) {
        try {
            return resourceLoader.getResource(resourceName).getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
