package dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto implements Serializable {

    private String login;
    private String password;
    private String firstName;
    private String lastName;

}
