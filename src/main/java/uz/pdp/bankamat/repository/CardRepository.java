package uz.pdp.bankamat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.bankamat.entity.Card;

import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card , UUID> {
    Optional<Card> findByCVV(@Size(max = 3) Integer CVV);
    Page<Card> findByBankId(Integer bank_id, Pageable pageable);
Optional<Card> findByCVVAndParol(Integer CVV, String parol);

@Modifying
    @Query(nativeQuery = true , value = "update cards set balance =:balance where cvv =:cvv")
    void updateByBalance(@Param("balance") Long balance , @Param("cvv") Integer cvv);
}
