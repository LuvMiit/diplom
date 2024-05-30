package org.ssmp.dtos.documents;

import lombok.Data;

@Data
public class SaveDocDTO {
    private String sellerCompanyName;
    private String fioGlavVrach;
    private String fioSeller;
    private String date;
    private String brand;
    private String vinNumber;
    private String carPlates;
    private String yearRelease;
    private String typeDocument;
    private String status;
}
