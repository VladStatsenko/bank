package entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountType implements Serializable {
    @Column(name = "type_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountTypeId;
    @Column(name = "type_code")
    private String typeCode;
    @Column(name = "type_name")
    private String typeName;
    @Column(name = "type_desc")
    private String typeDesc;

    @Column(name = "created_ts")
    private int createdTs;
    @Column(name = "updated_ts")
    private int updatedTs;

    @OneToMany(mappedBy = "type")
    private List<Account> accountList = new ArrayList<>();
}
