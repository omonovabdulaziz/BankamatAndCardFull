package uz.pdp.bankamat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.bankamat.entity.CardType;
import uz.pdp.bankamat.projection.PlastikTuriProjection;

import java.util.List;

@RepositoryRestResource(path = "plastikturi", excerptProjection = PlastikTuriProjection.class)
public interface PlastikTuriRepository extends JpaRepository<CardType, Integer> {
    @Query(nativeQuery = true, value = "select card_type_id from bankamat_card_type where bankamat_id =:bankamatId")
    List<Integer> findByBankamatId(@Param("bankamatId") Integer bankamatId);
}
