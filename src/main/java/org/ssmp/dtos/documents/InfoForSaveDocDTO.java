package org.ssmp.dtos.documents;

import lombok.Data;

@Data
public class InfoForSaveDocDTO {
    private String carPlates;
    private String sellerCompanyName;
    private String fioSeller;
    private String infoMyCompany;
    private String typeDocument;
    private String status;
}
