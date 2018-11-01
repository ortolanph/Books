package org.books.services;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
public class JasperReportService {

    @Autowired
    private StorageService storageService;

    public byte[] generatePDFReport(
        Report report,
        Map<String, Object> params,
        JRDataSource dataSource) {

        byte[] bytes = null;
        JasperReport jasperReport = null;

        try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
            InputStream jrxml = storageService.loadJrxmlFile(report);
            jasperReport = JasperCompileManager.compileReport(jrxml);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        }
        catch (JRException | IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }
}
