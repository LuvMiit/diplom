package org.ssmp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "crew")
public class Crew{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int crewID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_driver", referencedColumnName = "id_worker")
    private Staff driver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crew_car_id", referencedColumnName = "id_car")
    private CarSMP carSMP;
}
