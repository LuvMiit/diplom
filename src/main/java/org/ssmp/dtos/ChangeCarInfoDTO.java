package org.ssmp.dtos;

import lombok.Data;

@Data
public class ChangeCarInfoDTO {
    private String newCarPlates;
    private String oldCarPlates;
    private String fuel;
    private String type;
    private String status;
    private String mileage;
    private String vinNumber;
    private String brand;
    private String release;
    private String dateStart;
}
