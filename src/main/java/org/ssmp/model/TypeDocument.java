package org.ssmp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "document_types")
public class TypeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private long typeID;

    @Column(name = "name_type")
    private String typeName;
}
