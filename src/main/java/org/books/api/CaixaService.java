package org.books.api;

import org.books.Application;
import org.books.beans.Caixa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/api/boxes",
    produces = {"application/json;charset=UTF-8"})
public class CaixaService {


    @GetMapping("")
    public @ResponseBody List<Caixa> todasAsCaixas() {
        return new ArrayList<>();
    }

    @PostMapping("")
    public void cadastrarCaixa(@RequestBody Caixa caixa) {
        LOGGER.fine(caixa.toString());
    }

    private static final Logger LOGGER = Logger.getLogger(CaixaService.class.getName());
}
