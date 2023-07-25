package uz.pdp.bankamat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProccesPayment {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne
    private Card card;
    @OneToOne
    private Bankamat bankamat;
    @OneToMany
    private Set<Kupyuralar> kupyuralar;


}
