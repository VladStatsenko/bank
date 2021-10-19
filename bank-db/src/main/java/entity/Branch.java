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
@Table(name = "branch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Branch implements Serializable {
    @Column(name = "branch_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branchId;
    @Column(name = "branch_name")
    private String branchName;
    private String location;

    @Column(name = "created_ts")
    private int createdTs;
    @Column(name = "updated_ts")
    private int updatedTs;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank main;

    @ManyToMany(mappedBy = "branch")
    private List<Client> clients = new ArrayList<>();

}