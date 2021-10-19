package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile implements Serializable {
    @Column(name = "profile_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String login;
    private String password;
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "created_ts")
    private int createdTs;
    @Column(name = "updated_ts")
    private int updatedTs;

}