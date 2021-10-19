package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {
    @Column(name = "client_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "mid_name")
    private String midName;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "tin")
    private String TIN;

    @OneToOne(mappedBy = "client")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank client;

    @Column(name = "created_ts")
    private int createdTs;
    @Column(name = "updated_ts")
    private int updatedTs;

    @OneToMany(mappedBy = "account")
    private List<Account> accounts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "branch2client",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id"))
    private List<Branch> branch = new ArrayList<>();
}