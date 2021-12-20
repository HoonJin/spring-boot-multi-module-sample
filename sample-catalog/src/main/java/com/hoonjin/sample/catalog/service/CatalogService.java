package com.hoonjin.sample.catalog.service;

import com.hoonjin.sample.catalog.entity.Catalog;
import com.hoonjin.sample.catalog.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;

    public List<Catalog> getAllCatalog() {
        return catalogRepository.findAll();
    }
}
