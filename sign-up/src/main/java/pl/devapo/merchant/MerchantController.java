package pl.devapo.merchant;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/sign-up/merchant")
@RequiredArgsConstructor
class MerchantController {

    private final MerchantService merchantService;

    @PostMapping
    public Long create(@Valid @RequestBody MerchantDto merchantDto) {
        return merchantService.create(merchantDto);
    }
}
