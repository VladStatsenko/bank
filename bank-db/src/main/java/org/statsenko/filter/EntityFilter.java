package org.statsenko.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EntityFilter {

    private String firstName;
    private String lastName;
    private String midName;
    private Date birthDate;
    private String tin;
}
