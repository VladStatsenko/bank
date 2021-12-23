package dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto extends AbstractDto {

    private String numberAccount;
    private String client;
    private String bank;
    private String type;

}
