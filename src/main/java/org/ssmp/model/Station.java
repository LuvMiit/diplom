package org.ssmp.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "station")
public class Station {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_station")
    private int stationID;

    @Getter
    @Setter
    @Column(name = "name_station")
    private String stationName;

    @Getter
    @Setter
    @Column(name = "address")
    private String stationAddress;


    @Override
    public String toString() {
        return "Station{" +
                "stationID=" + stationID +
                ", stationName='" + stationName + '\'' +
                ", stationAddress='" + stationAddress + '\'' +
                '}';
    }
}
