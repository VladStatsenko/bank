package org.statsenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "bank",name = "profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile extends AbstractEntity {
    @Column(name = "profile_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;


    @OneToOne(mappedBy = "profile")
    private Client client;

    private String login;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;

}