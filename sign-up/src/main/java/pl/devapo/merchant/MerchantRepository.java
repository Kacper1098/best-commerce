package pl.devapo.merchant;

import org.springframework.data.jpa.repository.JpaRepository;

interface MerchantRepository extends JpaRepository<Merchant, Long> {
}
