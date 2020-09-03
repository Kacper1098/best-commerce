package pl.devapo.merchant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import pl.devapo.merchant.events.MerchantEventPublisher;
import pl.devapo.security.Encoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MerchantServiceTest {

    @MockBean
    private MerchantRepository merchantRepository;

    @MockBean
    private MerchantEventPublisher merchantEventPublisher;

    @Autowired
    private Encoder encoder;

    private MerchantService merchantService;

    @BeforeEach
    void setup() {
        merchantService = new MerchantService(merchantRepository, encoder, merchantEventPublisher);
    }

    @Test
    @DisplayName("Should execute method that publishes message on queue")
    void publishMessageTest() {
        MerchantDto mockedMerchantDto = mockMerchantDto("MerchantName", "merchant@gmail.com", "MerchantType"
                , "address", "OwnerName", "612451424", "password");
        doReturn(mockMerchant(mockedMerchantDto)).when(merchantRepository).save(any(Merchant.class));
        merchantService.create(mockedMerchantDto);
        verify(merchantEventPublisher, times(1)).publish(any(MerchantDto.class));
    }

    @Test
    @DisplayName("Should create merchant with encoded password")
    void encodePasswordTest() {
        MerchantDto mockedMerchantDto = mockMerchantDto("MerchantName", "merchant@gmail.com", "MerchantType"
                , "address", "OwnerName", "612451424", "password");
        Merchant merchant = merchantService.encodeMerchantPassword(mockedMerchantDto.toEntity());

        assertThat(BCrypt.checkpw("password", merchant.getPassword())).isTrue();

    }

    private MerchantDto mockMerchantDto(String name, String email, String type, String address,
                                        String ownerName, String phoneNumber, String password) {
        return MerchantDto
                .builder()
                .email(email)
                .name(name)
                .ownerName(ownerName)
                .password(password)
                .phoneNumber(phoneNumber)
                .address(address)
                .type(type)
                .build();
    }

    private Merchant mockMerchant(MerchantDto merchantDto) {
        Merchant merchant = merchantDto.toEntity();
        merchant.setId(1L);
        return merchant;
    }
}
