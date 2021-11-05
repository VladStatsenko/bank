package org.statsenko.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "bank",name = "branch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Branch extends AbstractEntity {
    @Column(name = "branch_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branchId;
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "br_location")
    private String location;

    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank main;

    @ManyToMany(mappedBy = "branch")
    private List<Client> clients = new ArrayList<>();

}