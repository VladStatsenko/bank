package dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientFilterDto {
    private String firstName;
    private String lastName;
    private String midName;
    @JsonProperty("birth_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;
    private String tin;
}
