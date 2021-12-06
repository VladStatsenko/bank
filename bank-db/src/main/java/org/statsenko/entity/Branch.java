package org.statsenko.entity;


import lombok.*;

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
@SequenceGenerator(name = "branch_id_seq", sequenceName = "branch_id_seq", initialValue = 1, allocationSize = 1)
public class Branch extends AbstractEntity {
    @Column(name = "branch_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_id_seq")
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