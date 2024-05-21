package org.ssmp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_maintenance")
    private long maintenanceID;

    @Column(name = "date_maintenance")
    private Calendar date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id_car")
    private CarSMP carSMP;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_id", referencedColumnName = "id_worker")
    private Staff worker;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "consumables_maintenance",
            joinColumns = @JoinColumn(
                    name = "id_maintenance"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_consumable"))
    private List<Consumables> consumablesInMaintenanceList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "operations_maintenance",
            joinColumns = @JoinColumn(
                    name = "id_maintenance"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_operation"))
    private List<Operations> operationsInMaintenanceList;


    @Override
    public String toString() {
        return "Maintenance{" +
                "maintenanceID=" + maintenanceID +
                ", date=" + date +
                ", carSMP=" + carSMP +
                ", worker=" + worker +
                ", consumablesInMaintenanceList=" + consumablesInMaintenanceList +
                ", operationsInMaintenanceList=" + operationsInMaintenanceList +
                '}';
    }
}
