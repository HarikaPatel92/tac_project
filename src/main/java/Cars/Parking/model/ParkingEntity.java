package Cars.Parking.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "parking")
public class ParkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
//    @Column(name = "created_at")
//    private Date createdAt;
//    @Column(name = "updated_at")
//    private Date updatedAt;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "is_wallet")
    private boolean isWallet;

    @Column(name = "car_type")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "location")
    @Enumerated(EnumType.STRING)
    private Location location;

    @Column(name = "deleted_at")
    private Date deletedAt;

}

