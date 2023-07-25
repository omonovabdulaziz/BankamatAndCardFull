package uz.pdp.bankamat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankamatDTO {
    @NotNull
    private Set<Integer> plastikTuriId;
    @NotNull
    private Long maxPayMoney;
    @NotNull
    private Long commision;
    @NotNull
    private Integer bankId;
    @NotNull
    private String hodimEmail;
    @NotNull
    private String manzil;

}
