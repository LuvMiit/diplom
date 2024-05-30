package org.ssmp.dtos.documents;

import lombok.Data;

@Data
public class InfoForWriteDownDocDTO {
    private String carPlates;
    private String mechID;
    private String status;
    private String reason;
    private String infoMyCompany;
}
