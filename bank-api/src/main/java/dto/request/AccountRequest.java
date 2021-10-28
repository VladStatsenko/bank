package dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    @JsonProperty("bank_id")
    private int bankId;
    @JsonProperty("account_type")
    private int accountType;
}
