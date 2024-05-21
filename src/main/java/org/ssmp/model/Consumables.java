package org.ssmp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "consumables")
public class Consumables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consumable")
    private long consumableID;

    @Column(name = "name_consumable")
    private String consumableName;

    @Override
    public String toString() {
        return "Consumables{" +
                "consumableID=" + consumableID +
                ", consumableName='" + consumableName + '\'' +
                '}';
    }
}
