package org.books.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Component
public class JasperReporService {

    @Autowired
    private StorageService storageService;

    public byte[] generatePDFReport(String inputFileName, Map<String, Object> params,
        JRDataSource dataSource) {
        byte[] bytes = null;
        JasperReport jasperReport = null;

        try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
            // Check if a compiled report exists
            String jrxml = storageService.loadJrxmlFile(inputFileName);
            jasperReport = JasperCompileManager.compileReport(jrxml);
            // Save compiled report. Compiled report is loaded next time
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            // return the PDF in bytes
            bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        }
        catch (JRException | IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }
}
