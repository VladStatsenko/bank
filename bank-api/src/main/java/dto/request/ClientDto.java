package dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.statsenko.entity.Client;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("mid_name")
    private String midName;
    @JsonProperty("birth_date")
    private Date birthDate;
    private String tin;
    private String password;
    private String login;
//    @JsonProperty("bank_id")
//    private int bankId;

    public ClientDto(Client client){
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.midName = client.getMidName();
        this.birthDate = client.getBirthDate();
        this.tin = client.getTIN();
        this.login = client.getProfile().getLogin();
        this.password = client.getProfile().getPassword();
//        this.bankId = client.getClient().getBankId();
    }
}
