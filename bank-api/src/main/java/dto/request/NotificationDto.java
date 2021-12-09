package dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationDto implements Serializable {

    private String name;
    private String status;
}
