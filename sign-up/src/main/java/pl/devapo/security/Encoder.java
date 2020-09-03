package pl.devapo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Encoder {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public String encode(String value) {
        return bCryptPasswordEncoder.encode(value);
    }
}
