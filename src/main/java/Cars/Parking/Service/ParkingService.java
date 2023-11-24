package Cars.Parking.Service;

import Cars.Parking.Repository.ParkingRepository;
import Cars.Parking.model.CarType;
import Cars.Parking.model.Location;
import Cars.Parking.model.ParkingEntity;
import Cars.Parking.model.ParkingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingService {
    @Autowired
    private ParkingRepository parkingRepository;

    public ParkingEntity enterVehicle(ParkingEntity vehicle) {
        if (isCellarSpaceAvailable()) {
            if (vehicle.getCarType() == CarType.SMALL) {
                vehicle.setLocation(Location.CELLAR);
                return parkingRepository.save(vehicle);
            } else {
                if (isBigSpaceAvailable()) {
                    vehicle.setLocation(Location.CELLAR);
                    return parkingRepository.save(vehicle);
                }
            }
        } else {
            int groundCount = getAvailabilityForGroundParking();
            if (groundCount > 0) {
                vehicle.setLocation(Location.GROUND);
                return parkingRepository.save(vehicle);
            }
            else{
                vehicle.setLocation(Location.REJECTED);
                return parkingRepository.save(vehicle);

            }
        }
            return null;

    }

    private boolean isBigSpaceAvailable() {
        List<ParkingEntity> bigCars = parkingRepository.findByLocationAndDeletedAtIsNull(Location.GROUND);
        int bigCapacity = 2;
        return bigCars.size() < bigCapacity;
    }

    private int getAvailabilityForGroundParking() {
        List<ParkingEntity> groundCars = parkingRepository.findByLocationAndDeletedAtIsNull(Location.GROUND);
        int groundCapacity = 5;
        return groundCapacity - groundCars.size();
    }

    private boolean isCellarSpaceAvailable() {
        List<ParkingEntity> cellarCars = parkingRepository.findByLocationAndDeletedAtIsNull(Location.CELLAR);
        int cellarCapacity = 5;
        return cellarCars.size() < cellarCapacity;
    }

    public Long getTotalVehicleCount(){
        return parkingRepository.count();
    }

    public ParkingEntity getVehicleStatus(Long id){
        System.out.println(id);
        return parkingRepository.findById(id).orElse(null);

    }

    public List<Object[] > getParkingStatus(){
        List<ParkingStatus>parkingStatus = new ArrayList<>();
        List<Object[]> result = parkingRepository.findParkingStatus();
        for(Object[] row : result){
            String location= (String) row[0];
            String carType = (String) row[1];
            Long count = (Long) row[2];
            System.out.println(location);
            System.out.println(carType);
            System.out.println(count);
            parkingStatus.add(new ParkingStatus(location, carType, count));
        }
        return result;
    }
}
