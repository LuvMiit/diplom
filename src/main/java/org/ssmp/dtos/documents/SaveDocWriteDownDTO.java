package org.ssmp.dtos.documents;

import lombok.Data;

@Data
public class SaveDocWriteDownDTO {
    private String stationName;
    private String carPlates;
    private String carID;
    private String brand;
    private String reason;
    private String fioGlavVrach;
    private String yearRelease;
    private String dateStart;
    private String mileage;
    private String vinNumber;
    private String fuel;
    private String fioMech;
}
