package uz.pdp.bankamat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.bankamat.entity.Bankamat;

public interface BankamatRepository extends JpaRepository<Bankamat, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = "update bankamat set balance =:balance where bankamat.id =:bankamatId")
    void updateByBankamat(@Param("balance") Long balance, @Param("bankamatId") Integer bankamatId);


}
