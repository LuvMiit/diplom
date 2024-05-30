package org.ssmp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    private long documentID;

    @Column(name = "car_plates")
    private String carPlates;

    @Lob
    @Column(name = "document_data")
    private byte[] documentData;

    @Column(name = "type_data")
    private String dataType;

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "type_document", referencedColumnName = "id_type")
    private TypeDocument typeDocument;

}
