package Cars.Parking.Controller;


import Cars.Parking.Service.ParkingService;
import Cars.Parking.model.ParkingEntity;
import Cars.Parking.model.ParkingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

//    @GetMapping("/temp")
//    public String temp(){
//
//        return "hi";
//    }

    @PostMapping("/enter")
    public ResponseEntity<ParkingEntity> enterVehicle(@RequestBody ParkingEntity vehicle){
        ParkingEntity savedVehicle=parkingService.enterVehicle(vehicle);
        if(savedVehicle!=null){
            return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/count")
    public Long getTotalVehicleCount() {
        return parkingService.getTotalVehicleCount();
    }
    @GetMapping("/status/{id}")
    public ParkingEntity getVehicleStatus(@PathVariable Long id) {
        ParkingEntity vehicle = parkingService.getVehicleStatus(id);
        return vehicle;
    }
@GetMapping("status")
    public List<Object[]> getParkingStatus(){
        return parkingService.getParkingStatus();
}
}
