package pl.devapo.merchant.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.devapo.merchant.MerchantDto;
import pl.devapo.merchant.MerchantRepository;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class MerchantEventListener {

    private final MerchantRepository merchantRepository;

    @JmsListener(destination = "${receiver-queue-merchant}")
    public void listener(String merchantMessage) {
        log.debug("Received new message from sign-up queue");

        try {
            MerchantDto merchantDto = new ObjectMapper().readValue(merchantMessage, MerchantDto.class);
            merchantRepository.save(merchantDto.toEntity());
        } catch (IOException e) {
            log.error("There is a problem with Profile mapping", e);
        }
    }
}
