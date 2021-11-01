package org.statsenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "bank",name = "bank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bank implements Serializable {
    @Column(name = "bank_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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