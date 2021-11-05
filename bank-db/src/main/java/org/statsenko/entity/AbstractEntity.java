package org.statsenko.entity;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
public class AbstractEntity implements Serializable {
}
