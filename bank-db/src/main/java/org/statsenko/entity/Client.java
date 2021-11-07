package org.statsenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "bank",name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends AbstractEntity {
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
    private String tin;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "branch2client",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id"))
    private List<Branch> branch = new ArrayList<>();
}