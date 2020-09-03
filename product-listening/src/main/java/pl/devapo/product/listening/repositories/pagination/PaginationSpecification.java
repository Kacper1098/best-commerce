package pl.devapo.product.listening.repositories.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
public class PaginationSpecification {
    private Integer rows;
    private Integer page;
    private String orderProperty;
    private String sortDirection;

    public PaginationSpecification() {
        this.rows = 100;
        this.page = 0;
        this.orderProperty = "";
        this.sortDirection = "asc";
    }

    public PageRequest pageRequest() {
        if (this.rows < 1) {
            throw new RuntimeException("Size cannot be zero or negative");
        }
        return PageRequest.of(this.page, this.rows, sortInfo(this.orderProperty, this.sortDirection));
    }

    private Sort sortInfo(String sortPropertyName, String direction) {
        if (Strings.isBlank(sortPropertyName) || Strings.isBlank(direction)) {
            return Sort.unsorted();
        }
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Sort by = Sort.by(sortPropertyName);
        return sortDirection.isAscending() ? by.ascending() : by.descending();
    }
}