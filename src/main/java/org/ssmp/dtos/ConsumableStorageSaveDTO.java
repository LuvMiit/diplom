package org.ssmp.dtos;

import lombok.Data;

@Data
public class ConsumableStorageSaveDTO {
    private String consumableName;
    private int quantity;
    private String storageAddress;
}
