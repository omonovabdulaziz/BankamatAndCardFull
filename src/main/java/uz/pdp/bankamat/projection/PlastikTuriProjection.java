package uz.pdp.bankamat.projection;


import org.springframework.data.rest.core.config.Projection;
import uz.pdp.bankamat.entity.CardType;

@Projection(types = CardType.class)
public interface PlastikTuriProjection {
    Integer getId();

    String getNomi();
}
