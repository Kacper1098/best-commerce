package pl.devapo.product.listening.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.devapo.product.listening.dtos.ProductDTO;
import pl.devapo.product.listening.repositories.pagination.PaginationSpecification;
import pl.devapo.product.listening.services.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> getProducts(PaginationSpecification paginationSpecification) {
        return ResponseEntity.ok(productService.findAll(paginationSpecification));
    }

}
