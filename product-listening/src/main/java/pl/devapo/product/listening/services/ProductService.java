package pl.devapo.product.listening.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.devapo.product.listening.dtos.ProductDTO;
import pl.devapo.product.listening.repositories.ProductRepository;
import pl.devapo.product.listening.repositories.pagination.PaginationSpecification;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductDTO> findAll(PaginationSpecification paginationSpecification) {
        return productRepository
                .findAllByInventoryGreaterThan(5L, paginationSpecification.pageRequest())
                .map(ProductDTO::of);
    }
}
