package org.books.api;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.books.beans.Caixa;
import org.books.services.BarcodeImageService;
import org.books.services.JasperReportService;
import org.books.services.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class CaixaController {

    @Autowired
    private JasperReportService reportService;

    @Autowired
    private BarcodeImageService codebarService;

    @GetMapping(value = "/api/boxes", produces = "application/json;charset=UTF-8")
    public @ResponseBody List<Caixa> todasAsCaixas() {
        return new ArrayList<>();
    }

    @PostMapping(value = "/api/boxes", consumes = "application/json;charset=UTF-8")
    public @ResponseBody Caixa cadastrarCaixa(@RequestBody Caixa caixa) {
        LOGGER.fine(caixa.toString());
        return caixa;
    }

    @GetMapping(value = "/api/boxes/report/{boxid}", produces = "application/pdf")
    public @ResponseBody ResponseEntity<byte[]> relatorioCaixas(@PathVariable("boxid") int boxid) {
        Caixa caixa1 = new Caixa();

        caixa1.setId(1L);
        caixa1.setTitulo("Caixa A - Original");
        caixa1.setDescricao("Diversos GIBIS");
        caixa1.setQrcode(codebarService.generateQRCode(caixa1.getTitulo(), 250, 250));

        Caixa caixa2 = new Caixa();

        caixa2.setId(2L);
        caixa2.setTitulo("Caixa B - Coleção Vermelha Marvel I");
        caixa2.setDescricao("Coleção Vermelha da Marvel");
        caixa2.setQrcode(codebarService.generateQRCode(caixa2.getTitulo(), 250, 250));

        Caixa caixa3 = new Caixa();

        caixa3.setId(2L);
        caixa3.setTitulo("Caixa C - Coleção Vermelha Marvel II");
        caixa3.setDescricao("Coleção Vermelha da Marvel");
        caixa3.setQrcode(codebarService.generateQRCode(caixa3.getTitulo(), 250, 250));

        Caixa caixa4 = new Caixa();

        caixa4.setId(2L);
        caixa4.setTitulo("Caixa D - Coleção Vermelha Marvel III");
        caixa4.setDescricao("Coleção Vermelha da Marvel");
        caixa4.setQrcode(codebarService.generateQRCode(caixa3.getTitulo(), 250, 250));

        List<Caixa> caixas = new ArrayList<>();

        caixas.add(caixa1);
        caixas.add(caixa2);
        caixas.add(caixa3);
        caixas.add(caixa4);

        byte[] bytes = reportService.generatePDFReport(
            Report.BOXES,
            new HashMap<>(),
            new JRBeanCollectionDataSource(caixas));

        return ResponseEntity
            .ok()
            .header("Content-Type", "application/pdf; charset=UTF-8")
            .header("Content-Disposition", "inline; filename=\"caixas.pdf\"")
            .body(bytes);
    }

    private static final Logger LOGGER = Logger.getLogger(CaixaController.class.getName());
}
