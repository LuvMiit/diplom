package org.ssmp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverDTO {
    private long driverID;
    private String fio;
}
