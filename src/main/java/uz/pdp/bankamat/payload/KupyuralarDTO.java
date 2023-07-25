package uz.pdp.bankamat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KupyuralarDTO {
    @NotNull(message = "bosh bolmasin")
    private Long count;
    @NotNull(message = "bosh bolmasin")
    private Integer pulBirligiId;
    @NotNull(message = "bosh bolmasin")
    private Integer nechta;
    @NotNull(message = "bosh bolmasin")
    private Integer bankamatId;
}
