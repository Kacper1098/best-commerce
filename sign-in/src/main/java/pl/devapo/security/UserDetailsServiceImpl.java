package pl.devapo.security;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.devapo.merchant.Merchant;
import pl.devapo.merchant.MerchantRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Merchant merchant = merchantRepository.findByEmail(username);

        if (merchant == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        return new UserPrincipal(merchant);
    }
}
