package pl.devapo.merchant;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String name;
    private String ownerName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
}
