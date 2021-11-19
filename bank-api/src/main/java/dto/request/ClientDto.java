package dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto implements Serializable {
    private String firstName;
    private String lastName;
    private String midName;
    @JsonProperty("birth_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;
    private String tin;
    private String login;

}
