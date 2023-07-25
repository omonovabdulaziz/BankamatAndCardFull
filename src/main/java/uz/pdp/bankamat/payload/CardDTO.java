package uz.pdp.bankamat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private Integer bankId;
    private Integer CVV;
    private String mijozFullName;
    private String parol;
    private Integer plastikTuri;
    private Long balance;
}
