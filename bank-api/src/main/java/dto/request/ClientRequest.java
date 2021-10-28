package dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("mid_name")
    private String midName;
    @JsonProperty("birth_date")
    private Date birthDate;
    private String tin;
    @JsonProperty("bank_id")
    private int bankId;
}
