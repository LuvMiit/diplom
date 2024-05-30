package org.ssmp.dtos.documents;

import lombok.Data;

@Data
public class InfoDocumentAcceptDTO {
    private String carPlates;
    private String mileage;
    private String vinNumber;
    private String stationName;
    private String fioSeller;
    private String sellerCompanyName;
    private String brand;
    private String release;
    private String status;
}
