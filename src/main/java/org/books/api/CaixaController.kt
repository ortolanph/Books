package org.books.api

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import org.books.beans.Caixa
import org.books.services.BarcodeImageService
import org.books.services.JasperReportService
import org.books.services.Report
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.logging.Logger

@RestController
class CaixaController {

    @Autowired
    private val reportService: JasperReportService? = null

    @Autowired
    private val codebarService: BarcodeImageService? = null

    @GetMapping(value = "/api/boxes", produces = arrayOf("application/json;charset=UTF-8"))
    @ResponseBody
    fun todasAsCaixas(): List<Caixa> {
        return ArrayList()
    }

    @PostMapping(value = "/api/boxes", consumes = arrayOf("application/json;charset=UTF-8"))
    @ResponseBody
    fun cadastrarCaixa(@RequestBody caixa: Caixa): Caixa {
        LOGGER.fine(caixa.toString())
        return caixa
    }

    @GetMapping(value = "/api/boxes/report/{boxid}", produces = arrayOf("application/pdf"))
    @ResponseBody
    fun relatorioCaixas(): ResponseEntity<ByteArray> {
        val caixa1 = Caixa()

        caixa1.id = 1L
        caixa1.titulo = "Caixa A - Original"
        caixa1.descricao = "Diversos GIBIS"
        caixa1.qrcode = codebarService!!.generateQRCode(caixa1.titulo!!, 250, 250)

        val caixa2 = Caixa()

        caixa2.id = 2L
        caixa2.titulo = "Caixa B - Coleção Vermelha Marvel I"
        caixa2.descricao = "Coleção Vermelha da Marvel"
        caixa2.qrcode = codebarService.generateQRCode(caixa2.titulo!!, 250, 250)

        val caixa3 = Caixa()

        caixa3.id = 2L
        caixa3.titulo = "Caixa C - Coleção Vermelha Marvel II"
        caixa3.descricao = "Coleção Vermelha da Marvel"
        caixa3.qrcode = codebarService.generateQRCode(caixa3.titulo!!, 250, 250)

        val caixa4 = Caixa()

        caixa4.id = 2L
        caixa4.titulo = "Caixa D - Coleção Vermelha Marvel III"
        caixa4.descricao = "Coleção Vermelha da Marvel"
        caixa4.qrcode = codebarService.generateQRCode(caixa3.titulo!!, 250, 250)

        val caixas = ArrayList<Caixa>()

        caixas.add(caixa1)
        caixas.add(caixa2)
        caixas.add(caixa3)
        caixas.add(caixa4)

        val bytes = reportService!!.generatePDFReport(
                Report.BOXES,
                HashMap(),
                JRBeanCollectionDataSource(caixas))

        return ResponseEntity
                .ok()
                .header("Content-Type", "application/pdf; charset=UTF-8")
                .header("Content-Disposition", "inline; filename=\"caixas.pdf\"")
                .body(bytes!!)
    }

    companion object {

        private val LOGGER = Logger.getLogger(CaixaController::class.java.name)
    }
}
