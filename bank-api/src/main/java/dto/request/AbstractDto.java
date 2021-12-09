package dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractDto implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

}
