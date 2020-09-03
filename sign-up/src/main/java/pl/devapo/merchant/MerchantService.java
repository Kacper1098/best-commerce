package pl.devapo.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import pl.devapo.merchant.events.MerchantEventPublisher;
import pl.devapo.security.Encoder;


@Service
@RequiredArgsConstructor
@Slf4j
class MerchantService {
    private final MerchantRepository merchantRepository;
    private final Encoder encoder;
    private final MerchantEventPublisher merchantEventPublisher;


    public Long create(MerchantDto merchantDto) {
        Merchant merchant = this.encodeMerchantPassword(merchantDto.toEntity());
        try {
            Long id = merchantRepository.save(merchant).getId();
            merchantEventPublisher.publish(merchantDto.fromEntity(merchant));
            return id;
        }
        catch (Exception exception) {
            log.error("Could not save to database!", exception);
            throw exception;
        }
    }


    public Merchant encodeMerchantPassword(Merchant merchant) {
        merchant.setPassword(encoder.encode(merchant.getPassword()));
        return merchant;
    }
}
