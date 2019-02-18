package org.books.services

import net.sf.jasperreports.engine.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.io.IOException

@Service
class JasperReportService {

    @Autowired
    private val storageService: StorageService? = null

    fun generatePDFReport(
            report: Report,
            params: Map<String, Any?>,
            dataSource: JRDataSource): ByteArray? {

        var bytes: ByteArray? = null
        var jasperReport: JasperReport? = null

        try {
            ByteArrayOutputStream().use { byteArray ->
                val jrxml = storageService!!.loadJrxmlFile(report)
                jasperReport = JasperCompileManager.compileReport(jrxml)
                val jasperPrint = JasperFillManager.fillReport(jasperReport!!, params, dataSource)
                bytes = JasperExportManager.exportReportToPdf(jasperPrint)
            }
        } catch (e: JRException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return bytes
    }
}
