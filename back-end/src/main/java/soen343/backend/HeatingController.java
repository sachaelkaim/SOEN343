package soen343.backend;


import com.fasterxml.jackson.databind.node.ObjectNode;
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
    public ResponseEntity<?> addZone(@PathVariable("locations") ArrayList<String> locations, @RequestBody ObjectNode objectNode ) {
        simulationService.addZone(locations, objectNode.get("userPrivilege").asText());
        return ResponseEntity.ok().body("Successfully added new zone.");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/heating/displayZones")
    @ResponseStatus( HttpStatus.OK )
    public List<HeatingModuleModel> displayZones(){
        return simulationService.displayZones();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/heating/setZoneTemperature")
    @ResponseStatus( HttpStatus.OK )
    public void setZoneTemperature(@RequestBody ObjectNode objectNode) {
        simulationService.setZoneTemperature(objectNode.get("userPrivilege").asText(), objectNode.get("zone").asText(), objectNode.get("period").asInt(), objectNode.get("temperature").asDouble());

    }

    @RequestMapping(method = RequestMethod.GET, value = "/heating/getCurrentTemperatures")
    @ResponseStatus( HttpStatus.OK )
    public Iterable<soen343.backend.room.Room> getCurrentTemperatures() {
        return simulationService.getCurrentTemperatures();

    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/heating/setFirstSummerMonth")
    @ResponseStatus( HttpStatus.OK )
    public void setFirstSummerMonth(@RequestBody ObjectNode objectNode) {
    	simulationService.setFirstSummerMonth(objectNode.get("userPrivilege").asText(), objectNode.get("firstSummerMonth").asInt());

    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/heating/setLastSummerMonth")
    @ResponseStatus( HttpStatus.OK )
    public void setLastSummerMonth(@RequestBody ObjectNode objectNode) {
        simulationService.setLastSummerMonth(objectNode.get("userPrivilege").asText(), objectNode.get("lastSummerMonth").asInt());

    }

    @RequestMapping(method = RequestMethod.POST, value = "/heating/setSeasonTemperature")
    @ResponseStatus( HttpStatus.OK )
    public void setSeasonTemperature(@RequestBody ObjectNode objectNode) {
        simulationService.setSeasonTemperature(objectNode.get("season").asInt(), objectNode.get("temperature").asDouble(),objectNode.get("privilege").asText());

    }
}
