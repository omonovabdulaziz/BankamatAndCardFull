package uz.pdp.bankamat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRegisterDTO {
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    @NotNull@Size(max = 10 , min = 3)
    private String password;
}
