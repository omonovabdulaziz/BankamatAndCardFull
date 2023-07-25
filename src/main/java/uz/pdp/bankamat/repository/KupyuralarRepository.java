package uz.pdp.bankamat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bankamat.entity.Kupyuralar;

import java.util.Optional;

public interface KupyuralarRepository extends JpaRepository<Kupyuralar, Integer> {
    Optional<Kupyuralar> findByBankHodimiEmailiForEnabledAndPasswordForEnabled(String bankHodimiEmailiForEnabled, String passwordForEnabled);

    Page<Kupyuralar> findByBankamatId(Integer bankamat_id, Pageable pageable);

    Optional<Kupyuralar> findByCount(Long count);
}
