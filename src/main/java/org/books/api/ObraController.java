package org.books.api;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.books.beans.Obra;
import org.books.beans.TipoObra;
import org.books.services.BarcodeImageService;
import org.books.services.JasperReportService;
import org.books.services.Report;
import org.books.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ObraController {

    @Autowired
    private JasperReportService reportService;

    @Autowired
    private BarcodeImageService barcodeImageService;

    @Autowired
    private StorageService storageService;

    @GetMapping(value = "/api/works/report/{boxid}", produces = "application/pdf")
    public @ResponseBody ResponseEntity<byte[]> relatorioLivros(@PathVariable("boxid") int boxid) {

        List<Obra> obras = new ArrayList<>();

        Obra obra1 = new Obra();

        obra1.setId(1L);
        obra1.setTitulo("Sonetos de Camões");
        obra1.setTipo(TipoObra.LIVRO);
        obra1.setTipoObra(TipoObra.LIVRO.name());
        obra1.setBarcode("9788585851620");
        obra1.setCode(barcodeImageService.generateEAN13Barcode(obra1.getBarcode(), 108, 48));
        obras.add(obra1);

        Obra obra2 = new Obra();

        obra2.setId(2L);
        obra2.setTitulo("When the Lion Roars - How to Overcome Temptation");
        obra2.setTipo(TipoObra.LIVRO);
        obra2.setTipoObra(TipoObra.LIVRO.name());
        obra2.setBarcode("9781944307981");
        obra2.setCode(barcodeImageService.generateEAN13Barcode(obra2.getBarcode(), 108, 48));
        obras.add(obra2);

        Obra obra3 = new Obra();

        obra3.setId(3L);
        obra3.setTitulo("SCRUM: A arte de fazer o dobro do trabalho na metade do tempo");
        obra3.setTipo(TipoObra.LIVRO);
        obra3.setTipoObra(TipoObra.LIVRO.name());
        obra3.setBarcode("9788544104514");
        obra3.setCode(barcodeImageService.generateEAN13Barcode(obra3.getBarcode(), 108, 48));
        obras.add(obra3);

        Obra obra4 = new Obra();
        
        obra4.setId(4L);
        obra4.setTitulo("Star Wars - O último vôo da Hanbinger");
        obra4.setTipo(TipoObra.COMIC);
        obra4.setTipoObra(TipoObra.COMIC.name());
        obra4.setBarcode("9788583683285");
        obra4.setCode(barcodeImageService.generateEAN13Barcode(obra4.getBarcode(), 108, 48));
        obras.add(obra4);

        Obra obra5 = new Obra();

        obra5.setId(5L);
        obra5.setTitulo("Star Wars - Infinitos - O Império Contra Ataca");
        obra5.setTipo(TipoObra.COMIC);
        obra5.setTipoObra(TipoObra.COMIC.name());
        obra5.setBarcode("9788542607031");
        obra5.setCode(barcodeImageService.generateEAN13Barcode(obra5.getBarcode(), 108, 48));
        obras.add(obra5);

        Obra obra6 = new Obra();

        obra6.setId(6L);
        obra6.setTitulo("Star Wars - Infinitos - O Retorno de Jedi");
        obra6.setTipo(TipoObra.COMIC);
        obra6.setTipoObra(TipoObra.COMIC.name());
        obra6.setBarcode("9788583682981");
        obra6.setCode(barcodeImageService.generateEAN13Barcode(obra6.getBarcode(), 108, 48));
        obras.add(obra6);
        
        Map<String, Object> properties = new HashMap<>();

        properties.put("LOGO", storageService.loadLogo());
        properties.put("PARAM_CAIXA", "Caixa M - Caixa Template");
        properties.put("PARAM_DESCRICAO", "Esta é uma caixa template somente para teste de relatório. Nela se encontram qualquer e todo o tipo de Obra.");
        properties.put("PARAM_QR_CODE", barcodeImageService.generateQRCode("Caixa M - Caixa Template", 72, 72));

        byte[] bytes = reportService.generatePDFReport(
            Report.WORKS,
            properties,
            new JRBeanCollectionDataSource(obras));

        return ResponseEntity
            .ok()
            .header("Content-Type", "application/pdf; charset=UTF-8")
            .header("Content-Disposition", "inline; filename=\"caixa-a.pdf\"")
            .body(bytes);
    }

}
