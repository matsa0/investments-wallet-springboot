package br.ufop.web2.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDomain {

    private UUID id;
    private String name;
    private String email;
    private String password;

    private List<InvestmentDomain> investments;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
