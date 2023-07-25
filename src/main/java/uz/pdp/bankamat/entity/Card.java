package uz.pdp.bankamat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cards")
@EntityListeners(AuditingEntityListener.class)
public class Card {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Bank bank;
    @Column(nullable = false, unique = true)
    private Integer CVV;
    @Column(nullable = false)
    private String mijozFullName;
    @Column(nullable = false)
    private String parol;
    @OneToOne
    private CardType plastikTuri;
    @Column(nullable = false)
    private Long balance;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updateAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updateBy;
}
