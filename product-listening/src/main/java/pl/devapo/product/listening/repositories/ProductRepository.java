package pl.devapo.product.listening.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.devapo.product.listening.models.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String>, JpaSpecificationExecutor<Product> {
    Page<Product> findAllByInventoryGreaterThan(Long inventory, Pageable pageable);
}
