package uz.pdp.bankamat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.bankamat.entity.PulBirligi;

@RepositoryRestResource(path = "pulBirligi")
public interface PulBirligiRepository extends JpaRepository<PulBirligi , Integer> {
}
