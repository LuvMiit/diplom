package org.ssmp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "operations")
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operation")
    private long operationID;

    @Column(name = "name_operation")
    private String operationName;

    @Override
    public String toString() {
        return "Operations{" +
                "operationID=" + operationID +
                ", operationName='" + operationName + '\'' +
                '}';
    }
}
