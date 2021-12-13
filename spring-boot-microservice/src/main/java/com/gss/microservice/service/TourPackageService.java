package com.gss.microservice.service;

import com.gss.microservice.entity.TourPackage;
import com.gss.microservice.repository.TourPackageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TourPackageService {

    @Autowired
    private TourPackageRepository repository;

    public TourPackage createTourPackage(String code, String name) {
        log.debug("create: code = %s, name= %s", code, name);
        return repository.findById(code)
                .orElse(repository.save(new TourPackage(code, name)));
    }

    public Iterable<TourPackage> findAllTourPackage() {
        return repository.findAll();
    }

    public long total() {
        return repository.count();
    }
}
