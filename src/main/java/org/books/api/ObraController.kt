package org.books.api

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import org.books.beans.Obra
import org.books.beans.TipoObra
import org.books.services.BarcodeImageService
import org.books.services.JasperReportService
import org.books.services.Report
import org.books.services.StorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.util.ArrayList
import java.util.HashMap
import javax.servlet.http.HttpServletRequest

@RestController
class ObraController {

    @Autowired
    private val reportService: JasperReportService? = null

    @Autowired
    private val barcodeImageService: BarcodeImageService? = null

    @Autowired
    private val storageService: StorageService? = null

    @GetMapping(value = "/api/works/report/{boxid}", produces = arrayOf("application/pdf"))
    @ResponseBody
    fun relatorioLivros(@PathVariable("boxid") boxid: Int,
                        @RequestAttribute("LOGGED_USER_ID") userId : Int): ResponseEntity<ByteArray> {

        val obras = ArrayList<Obra>()

        val obra1 = Obra()

        obra1.id = 1L
        obra1.titulo = "Sonetos de Camões"
        obra1.tipo = TipoObra.LIVRO
        obra1.tipoObra = TipoObra.LIVRO.name
        obra1.barcode = "9788585851620"
        obra1.code = barcodeImageService!!.generateEAN13Barcode(obra1.barcode!!, 108, 48)
        obras.add(obra1)

        val obra2 = Obra()

        obra2.id = 2L
        obra2.titulo = "When the Lion Roars - How to Overcome Temptation"
        obra2.tipo = TipoObra.LIVRO
        obra2.tipoObra = TipoObra.LIVRO.name
        obra2.barcode = "9781944307981"
        obra2.code = barcodeImageService.generateEAN13Barcode(obra2.barcode!!, 108, 48)
        obras.add(obra2)

        val obra3 = Obra()

        obra3.id = 3L
        obra3.titulo = "SCRUM: A arte de fazer o dobro do trabalho na metade do tempo"
        obra3.tipo = TipoObra.LIVRO
        obra3.tipoObra = TipoObra.LIVRO.name
        obra3.barcode = "9788544104514"
        obra3.code = barcodeImageService.generateEAN13Barcode(obra3.barcode!!, 108, 48)
        obras.add(obra3)

        val obra4 = Obra()

        obra4.id = 4L
        obra4.titulo = "Star Wars - O último vôo da Hanbinger"
        obra4.tipo = TipoObra.COMIC
        obra4.tipoObra = TipoObra.COMIC.name
        obra4.barcode = "9788583683285"
        obra4.code = barcodeImageService.generateEAN13Barcode(obra4.barcode!!, 108, 48)
        obras.add(obra4)

        val obra5 = Obra()

        obra5.id = 5L
        obra5.titulo = "Star Wars - Infinitos - O Império Contra Ataca"
        obra5.tipo = TipoObra.COMIC
        obra5.tipoObra = TipoObra.COMIC.name
        obra5.barcode = "9788542607031"
        obra5.code = barcodeImageService.generateEAN13Barcode(obra5.barcode!!, 108, 48)
        obras.add(obra5)

        val obra6 = Obra()

        obra6.id = 6L
        obra6.titulo = "Star Wars - Infinitos - O Retorno de Jedi"
        obra6.tipo = TipoObra.COMIC
        obra6.tipoObra = TipoObra.COMIC.name
        obra6.barcode = "9788583682981"
        obra6.code = barcodeImageService.generateEAN13Barcode(obra6.barcode!!, 108, 48)
        obras.add(obra6)

        val properties = HashMap<String, Any?>()

        properties["LOGO"] = storageService?.loadLogo()
        properties["PARAM_CAIXA"] = "Caixa M - Caixa Template"
        properties["PARAM_DESCRICAO"] = "Esta é uma caixa template somente para teste de relatório. Nela se encontram qualquer e todo o tipo de Obra."
        properties["PARAM_QR_CODE"] = barcodeImageService.generateQRCode("Caixa M - Caixa Template", 72, 72)

        val bytes = reportService!!.generatePDFReport(
                Report.WORKS,
                properties,
                JRBeanCollectionDataSource(obras))

        return ResponseEntity
                .ok()
                .header("Content-Type", "application/pdf; charset=UTF-8")
                .header("Content-Disposition", "inline; filename=\"caixa-a.pdf\"")
                .body(bytes!!)
    }

}
