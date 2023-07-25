package uz.pdp.bankamat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.bankamat.entity.Kupyuralar;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProccessPaymentDTO {
    @NotNull
    private Integer cardCvv;
    @NotNull
    private Integer bankamatId;
    @NotNull
    private Set<Long> kupyuralar;
    @NotNull
    private Long cardParol;
}
