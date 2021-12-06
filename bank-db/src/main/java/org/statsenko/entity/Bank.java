package org.statsenko.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "bank",name = "bank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "bank_id_seq", sequenceName = "bank_id_seq", initialValue = 1, allocationSize = 1)
public class Bank extends AbstractEntity {
    @Column(name = "bank_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bank_id_seq")
    private int bankId;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;

    @OneToMany(mappedBy = "main")
    private List<Branch> branchesList = new ArrayList<>();


    @OneToMany(mappedBy = "bankAccounts")
    private List<Account> accountList = new ArrayList<>();
}