package soen343.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*") //to unblock request to/from react
@RequestMapping("api/")
public class HeatingController {

    @Autowired
    private SimulationService simulationService;

    @RequestMapping(method = RequestMethod.GET, value = "/heating/availableLocations")
    @ResponseStatus( HttpStatus.OK )
    public java.util.ArrayList<String> getAvailableLocations(){
         return simulationService.availableLocations();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/heating/{locations}")
    @ResponseStatus( HttpStatus.OK )
    public ResponseEntity<?> addZone(@PathVariable("locations") ArrayList<String> locations) {
        simulationService.addZone(locations);
        return ResponseEntity.ok().body("Successfully added new zone.");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/heating/displayZones")
    @ResponseStatus( HttpStatus.OK )
    public List<HeatingModuleModel> displayZones(){
        return simulationService.displayZones();
    }

}
