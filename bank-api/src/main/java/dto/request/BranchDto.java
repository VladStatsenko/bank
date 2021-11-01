package dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.statsenko.entity.Branch;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {

    @JsonProperty("bank_id")
    private int bankId;
    private String name;
    private String location;

    public BranchDto(Branch branch){
        this.name = branch.getBranchName();
        this.bankId = branch.getMain().getBankId();
        this.location = branch.getLocation();
    }
}
