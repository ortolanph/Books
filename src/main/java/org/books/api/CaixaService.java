package org.books.api;

import org.books.Application;
import org.books.beans.Caixa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class CaixaService {


    @GetMapping(value = "/api/boxes", produces = "application/json;charset=UTF-8")
    public @ResponseBody List<Caixa> todasAsCaixas() {
        return new ArrayList<>();
    }

    @PostMapping(value = "/api/boxes", consumes = "application/json;charset=UTF-8")
    public @ResponseBody Caixa cadastrarCaixa(@RequestBody Caixa caixa) {
        LOGGER.fine(caixa.toString());
        return caixa;
    }

    @GetMapping(value = "/api/boxes/report", produces = "")
    public byte[] relatorioCaixas() {
        return null;
    }

    private static final Logger LOGGER = Logger.getLogger(CaixaService.class.getName());
}
