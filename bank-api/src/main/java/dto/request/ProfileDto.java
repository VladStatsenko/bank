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
public class ProfileDto implements Serializable {

    private String login;
    private String password;
    private String firstName;
    private String lastName;

}
