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
@Table(name = "bank")
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
    private int createdTs;
    @Column(name = "updated_ts")
    private int updatedTs;

    @OneToMany(mappedBy = "main")
    private List<Branch> branchesList = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<Client> clientList = new ArrayList<>();

    @OneToMany(mappedBy = "bankAccounts")
    private List<Account> accountList = new ArrayList<>();
}