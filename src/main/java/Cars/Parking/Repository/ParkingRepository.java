package Cars.Parking.Repository;

import Cars.Parking.model.Location;
import Cars.Parking.model.ParkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingRepository extends JpaRepository<ParkingEntity,Long> {
    List<ParkingEntity>findByLicensePlateAndDeletedAtIsNull(String licensePlate);
    List<ParkingEntity> findByLocationAndDeletedAtIsNull(Location location);
    @Query(value = "SELECT location, car_type, COUNT(*) " +
            "FROM parking " +
            "WHERE deleted_at IS NULL " +
            "GROUP BY location, car_type",
            nativeQuery = true)
    List<Object[]> findParkingStatus();

}
