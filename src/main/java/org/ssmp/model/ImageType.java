package org.ssmp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image_types")
@NoArgsConstructor
@Data
public class ImageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_type")
    private long typeID;

    @Column(name = "type_name")
    private String typeName;
}
