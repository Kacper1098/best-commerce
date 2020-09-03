package pl.devapo.merchant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import pl.devapo.merchant.controller.BaseWebMvcControllerTest;
import pl.devapo.security.Encoder;

import java.security.NoSuchAlgorithmException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MerchantControllerTest extends BaseWebMvcControllerTest {

    private final String PATH = "/sign-up/merchant";

    @MockBean
    private Encoder encoder;

    @MockBean
    private MerchantService merchantService;

    @Test
    @DisplayName("Should create and return merchant")
    void createMerchantTest() throws Exception {
        MerchantDto mockedMerchantDto = mockMerchantDto("MerchantName", "merchant@gmail.com", "MerchantType"
                ,"address","OwnerName", "612451424", "password");

        doReturn(1L).when(merchantService).create(any(MerchantDto.class));
        doReturn("hashedPassword").when(encoder).encode(anyString());

        mockMvc.perform(
                    post(PATH)
                            .content(objectMapper.writeValueAsString(mockedMerchantDto))
                            .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }


    @Test
    @DisplayName("Should throw error if merchant password is shorten than 6")
    void createMerchantInvalidPasswordLength() throws Exception {
        MerchantDto mockedMerchant = mockMerchantDto("MerchantName", "merchant@gmail.com", "MerchantType",
                                    "address","OwnerName", "512351231", "pass");
        mockMvc.perform(
                    post(PATH)
                            .content(objectMapper.writeValueAsString(mockedMerchant))
                            .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Should throw error if merchant password does not consists of alphanumerics only")
    void createMerchantInvalidAlphanumerics() throws Exception{
        MerchantDto mockedMerchant = mockMerchantDto("MerchantName", "merchant@gmail.com", "MerchantType",
                "address","OwnerName", "512351231", "pass--123");

        mockMvc.perform(
                    post(PATH)
                        .content(objectMapper.writeValueAsString(mockedMerchant))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    private MerchantDto mockMerchantDto(String name, String email, String type,String address,
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

    private Merchant mockMerchant(MerchantDto merchantDto) throws NoSuchAlgorithmException {
        Merchant merchant = merchantDto.toEntity();
        merchant.setId(1L);
        return merchant;
    }
}
