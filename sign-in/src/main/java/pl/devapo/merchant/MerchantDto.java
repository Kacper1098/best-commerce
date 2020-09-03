package pl.devapo.merchant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@Builder
public class MerchantDto {
    @NotEmpty(message = "Merchant Type is required.")
    private String type;
    @NotEmpty(message = "Merchant Name is required.")
    private String name;
    @NotEmpty(message = "Owner name is required.")
    private String ownerName;
    @NotEmpty(message = "Address is required.")
    private String address;
    @NotEmpty(message = "Phone number is required.")
    private String phoneNumber;
    @NotEmpty(message = "Email Address is required.")
    @Email
    private String email;
    @NotEmpty(message = "Password is required.")
    private String password;

    public MerchantDto() {}

    public MerchantDto(String address, String type, String name, String ownerName, String phoneNumber, String email, String password) {
        this.address = address;
        this.type = type;
        this.name = name;
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public Merchant toEntity() {
        return Merchant
                .builder()
                .type(this.type)
                .phoneNumber(this.phoneNumber)
                .password(this.password)
                .ownerName(this.ownerName)
                .name(this.name)
                .email(this.email)
                .build();
    }

}
