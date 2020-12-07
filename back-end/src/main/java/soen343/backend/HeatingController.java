package soen343.backend;


import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Heating controller.
 */
@RestController
@CrossOrigin("*") //to unblock request to/from react
@RequestMapping("api/")
public class HeatingController {

    @Autowired
    private SimulationService simulationService;

    /**
     * Get available locations java . util . array list.
     *
     * @return the java . util . array list
     */
    @RequestMapping(method = RequestMethod.GET, value = "/heating/availableLocations")
    @ResponseStatus( HttpStatus.OK )
    public java.util.ArrayList<String> getAvailableLocations(){
         return simulationService.availableLocations();
    }

    /**
     * Add zone response entity.
     *
     * @param locations  the locations
     * @param objectNode the object node
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST, value = "/heating/{locations}")
    @ResponseStatus( HttpStatus.OK )
    public ResponseEntity<?> addZone(@PathVariable("locations") ArrayList<String> locations, @RequestBody ObjectNode objectNode ) {
        simulationService.addZone(locations, objectNode.get("userPrivilege").asText());
        return ResponseEntity.ok().body("Successfully added new zone.");
    }

    /**
     * Display zones list.
     *
     * @return the list
     */
    @RequestMapping(method = RequestMethod.GET, value = "/heating/displayZones")
    @ResponseStatus( HttpStatus.OK )
    public List<HeatingModuleModel> displayZones(){
        return simulationService.displayZones();
    }

    /**
     * Sets zone temperature.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.POST, value = "/heating/setZoneTemperature")
    @ResponseStatus( HttpStatus.OK )
    public void setZoneTemperature(@RequestBody ObjectNode objectNode) {
        simulationService.setZoneTemperature(objectNode.get("userPrivilege").asText(), objectNode.get("zone").asText(), objectNode.get("period").asInt(), objectNode.get("temperature").asDouble());

    }

    /**
     * Gets current temperatures.
     *
     * @return the current temperatures
     */
    @RequestMapping(method = RequestMethod.GET, value = "/heating/getCurrentTemperatures")
    @ResponseStatus( HttpStatus.OK )
    public Iterable<soen343.backend.room.Room> getCurrentTemperatures() {
        return simulationService.getCurrentTemperatures();

    }

    /**
     * Sets season temperature.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.POST, value = "/heating/setSeasonTemperature")
    @ResponseStatus( HttpStatus.OK )
    public void setSeasonTemperature(@RequestBody ObjectNode objectNode) {
        simulationService.setSeasonTemperature(objectNode.get("seasons").asInt(), objectNode.get("temperature").asDouble(),objectNode.get("userPrivilege").asText());
    }

    /**
     * Sets first summer month.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.POST, value = "/heating/setFirstSummerMonth")
    @ResponseStatus( HttpStatus.OK )
    public void setFirstSummerMonth(@RequestBody ObjectNode objectNode) {
        simulationService.setFirstSummerMonth(objectNode.get("userPrivilege").asText(), objectNode.get("firstSummerMonth").asInt());

    }

    /**
     * Sets last summer month.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.POST, value = "/heating/setLastSummerMonth")
    @ResponseStatus( HttpStatus.OK )
    public void setLastSummerMonth(@RequestBody ObjectNode objectNode) {
        simulationService.setLastSummerMonth(objectNode.get("userPrivilege").asText(), objectNode.get("lastSummerMonth").asInt());

    }
}
