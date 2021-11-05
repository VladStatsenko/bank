package dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.statsenko.entity.Account;
import org.statsenko.entity.AccountType;
import org.statsenko.entity.Bank;
import org.statsenko.entity.Client;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable {

    private String numberAccount;
    private Client account;
    private Integer clientId;
    private Integer bankId;
    private Integer typeId;

}
