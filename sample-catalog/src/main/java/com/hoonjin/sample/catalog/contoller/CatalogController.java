package com.hoonjin.sample.catalog.contoller;

import com.hoonjin.sample.catalog.domain.ResponseCatalog;
import com.hoonjin.sample.catalog.entity.Catalog;
import com.hoonjin.sample.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;
    private final Environment environment;

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        List<Catalog> allCatalog = catalogService.getAllCatalog();
        List<ResponseCatalog> res = allCatalog.stream().map(ResponseCatalog::of).collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }

}
