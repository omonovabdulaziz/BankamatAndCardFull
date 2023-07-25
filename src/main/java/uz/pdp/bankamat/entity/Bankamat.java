package uz.pdp.bankamat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bankamat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany
    private Set<CardType> cardType;
    @Column(nullable = false)
    private Long maxPayMoney;
    @Column(nullable = false)
    private Long commision;
    @ManyToOne
    private Bank bank;
    @OneToOne
    private Employee employee;
    @NotNull
    private Long balance;
    @Column(nullable = false)
    private String manzil;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updateAt;

}
