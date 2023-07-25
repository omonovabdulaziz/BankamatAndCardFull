package uz.pdp.bankamat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Kupyuralar {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private Long count;
    @OneToOne
    private PulBirligi pulBirligi;
    @Column(nullable = false)
    private Integer nechta;
    @ManyToOne
    private Bankamat bankamat;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updateAt;
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @LastModifiedBy
    private String updateBy;
    private boolean enabled = false;
    private String passwordForEnabled;
    private String bankHodimiEmailiForEnabled;

}
