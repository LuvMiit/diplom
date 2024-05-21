package org.ssmp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="car_images")
@Builder
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_image")
    private long imageID;

    @Column(name = "name_image")
    private String imageName;

    @Column(name = "type_image")
    private String typeImage;

    @Column(name="date_created")
    private Calendar createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="car_id", referencedColumnName = "id_car")
    private CarSMP car;

    @Lob
    @Column(name="image_data")
    private byte[] dataImage;
}
