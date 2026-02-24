package br.ufop.web2.entity;

import br.ufop.web2.enums.InvestmentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "investments")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private InvestmentType type;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private String symbol;
    @Column(nullable = false)
    private Double purchasePrice;
    @Column(nullable = false)
    private LocalDateTime purchaseDateTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;


    @PrePersist
    public void beforeSave() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
