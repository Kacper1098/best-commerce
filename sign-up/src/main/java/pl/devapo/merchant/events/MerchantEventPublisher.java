package pl.devapo.merchant.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import pl.devapo.merchant.MerchantDto;

@Component
@RequiredArgsConstructor
@Slf4j
public class MerchantEventPublisher {
    private final JmsTemplate jmsTemplate;
    @Value("${sender-queue-merchant}")
    private String queue;

    public void publish(MerchantDto dto) {
        try {
            jmsTemplate.convertAndSend(queue, convertMessageToString(dto));
            log.debug("Send an message to {} queue: {}", queue, dto);
        } catch (Exception e) {
            log.error("Could not send message {} to queue {}", dto, queue, e);
        }
    }

    private <T> String convertMessageToString(T event) {
        try {
            return new ObjectMapper().writeValueAsString(event);
        } catch (JsonProcessingException e) {
            log.error("Could not convert message to String: {}", event, e);
        }
        return null;
    }
}
