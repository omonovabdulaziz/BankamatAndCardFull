package uz.pdp.bankamat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.bankamat.entity.Bank;
import uz.pdp.bankamat.projection.BankProjection;

@RepositoryRestResource(path = "bank", excerptProjection = BankProjection.class)
public interface BankRepository extends JpaRepository<Bank, Integer> {
}
