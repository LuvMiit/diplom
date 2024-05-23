package org.ssmp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Data
@Table(name = "storage_consumables")
public class ConsumablesStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entry")
    private long entryID;

    @ManyToOne
    @JoinColumn(name = "storage_id_storage", referencedColumnName = "id_storage")
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "consumables_id_consumable", referencedColumnName = "id_consumable")
    private Consumables consumable;

    @Column(name = "quantity")
    private int quantity;
}
