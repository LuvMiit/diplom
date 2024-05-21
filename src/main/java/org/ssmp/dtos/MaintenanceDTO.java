package org.ssmp.dtos;

import lombok.Data;
import org.ssmp.model.Consumables;
import org.ssmp.model.Operations;

import java.util.Calendar;
import java.util.List;

@Data
public class MaintenanceDTO {

    private long maintenanceID;
    private Calendar date;
    private String carPlates;
    private long employeeID;
    private String firstName;
    private String surName;
    private String patronimyc;
    private List<Consumables> usedConsumables;
    private List<Operations> usedOperations;
}
