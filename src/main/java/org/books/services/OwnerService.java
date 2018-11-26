package org.books.services;

import org.books.beans.LoggedOwner;
import org.books.entities.Owner;
import org.books.persistence.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository repository;

    @Autowired
    private SecurityService securityService;

    public List<Owner> procurarOwner(Owner owner) {
        return repository.findByName(owner.getName());
    }

    public LoggedOwner criarOwner(Owner owner) {
        LoggedOwner loggedOwner = new LoggedOwner();

        loggedOwner.setName(owner.getName());
        loggedOwner.setToken(securityService.gerarToken(owner));

        return loggedOwner;
    }
}
