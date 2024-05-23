package org.ssmp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name="car_id", referencedColumnName = "id_car")
    private CarSMP car;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id_type")
    private ImageType type;

    @Lob
    @Column(name="image_data")
    private byte[] dataImage;
}
