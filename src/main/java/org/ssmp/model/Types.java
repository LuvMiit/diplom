package org.ssmp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "types")
public class Types {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private int typeID;

    @Column(name = "name_type")
    private String typeName;
}
