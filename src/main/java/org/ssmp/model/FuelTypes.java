package org.ssmp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "types_fuel")
public class FuelTypes {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typefuel")
    private int fuelID;

    @Getter
    @Setter
    @Column(name = "name_fuel")
    private String fuelName;

    @Override
    public String toString() {
        return "FuelTypes{" +
                "fuelID=" + fuelID +
                ", fuelName='" + fuelName + '\'' +
                '}';
    }
}
