package org.ssmp.dtos;

import lombok.Data;

@Data
public class CarPostDTO {
    private String carPlates;
    private double mileage;
    private String vin;
    private String stationName;
    private String status;
    private String type;
    private String fuel;
}
