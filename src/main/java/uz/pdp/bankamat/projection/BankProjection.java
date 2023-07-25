package uz.pdp.bankamat.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.bankamat.entity.Bank;

import java.sql.Timestamp;

@Projection(types = Bank.class)
public interface BankProjection {
    Integer getId();

    String getNomi();

    Timestamp getCreatedAt();

    Timestamp getUpdateAt();
}
