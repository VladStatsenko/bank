package dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.statsenko.entity.Account;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @JsonProperty("bank_id")
    private int bankId;
    @JsonProperty("account_type")
    private int accountType;

    public AccountDto(Account account){
        this.accountType = account.getType().getAccountTypeId();
        this.bankId = account.getBankAccounts().getBankId();
    }
}
