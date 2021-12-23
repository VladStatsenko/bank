package dto.request;

import lombok.*;

import java.io.Serializable;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDto extends AbstractDto
{
    private String bankName;
}
