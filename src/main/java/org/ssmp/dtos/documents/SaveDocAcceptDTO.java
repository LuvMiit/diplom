package org.ssmp.dtos.documents;

import lombok.Data;

@Data
public class SaveDocAcceptDTO {
    private String carPlates;
    private String sellerCompanyName;
    private String myStationName;
    private String fioGlavVrach;
    private String brand;
    private String vinNumber;
    private String release;
    private String mileage;
    private String fioSeller;
}
