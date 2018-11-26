package org.books.api;

import org.books.beans.LoggedOwner;
import org.books.entities.Owner;
import org.books.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/public", produces = "application/json")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<LoggedOwner> register(@RequestBody Owner owner) {
        List<Owner> possibleOwners = ownerService.procurarOwner(owner);

        if (!possibleOwners.isEmpty()) {
            return ResponseEntity
                .badRequest()
                .build();
        }

        return ResponseEntity
            .ok(ownerService.criarOwner(owner));
    }

}
