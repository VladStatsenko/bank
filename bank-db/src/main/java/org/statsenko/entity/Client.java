package org.statsenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", initialValue = 1, allocationSize = 1)
public class Client extends AbstractEntity {
    @Column(name = "client_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "client_id_seq")
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

    @OneToOne(mappedBy = "client")
    private Profile profile;

    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Account> accounts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "branch2client",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id"))
    private List<Branch> branch = new ArrayList<>();
}