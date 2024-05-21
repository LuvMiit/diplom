package org.ssmp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "cars_smp")
public class CarSMP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private long carID;

    @ManyToOne
    @JoinColumn(name = "station_id", referencedColumnName = "id_station")
    private Station station;

    @Column(name = "vin_number")
    private String vinNumber;

    @Column(name = "car_plate")
    private String carPlates;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fuel_id", referencedColumnName = "id_typefuel")
    private FuelTypes fuelType;

    @Column(name = "mileage")
    private double mileage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "id_type")
    private Types type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id_status")
    private Status status;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "car_id", referencedColumnName = "id_car")
//    private List<Images> imagesList = new ArrayList<>();





    @Override
    public String toString() {
        return "CarSMP{" +
                "carID=" + carID +
                ", station=" + station.getStationName() +
                ", vinNumber='" + vinNumber + '\'' +
                ", carPlates='" + carPlates + '\'' +
                ", fuelType='" + fuelType.getFuelName() + '\'' +
                ", mileage=" + mileage +
                ", type=" + type.getTypeName() +
                ", status=" + status.getStatusName() +
                '}';
    }
}
