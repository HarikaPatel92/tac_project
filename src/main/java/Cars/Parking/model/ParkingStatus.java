package Cars.Parking.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ParkingStatus {
    private String location;
    private String carType;
    private Long count;
}
