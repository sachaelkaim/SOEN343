package soen343.backend;


import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import soen343.backend.state.StateService;

/**
 * The type Core module controller.
 */
@RestController
@CrossOrigin("http://localhost:3000") //to unblock request to/from react
@RequestMapping("api/")
public class CoreModuleController {

    @Autowired
    private SimulationService simulationService;

    @Autowired
    private StateService state;

    /**
     * Get date and time string.
     *
     * @return the string
     */
    @RequestMapping(method = RequestMethod.GET, value = "/core/dateAndTime")
    @ResponseStatus( HttpStatus.OK )
    public String getDateAndTime(){
        if(state.getCurrentState()){
            return CoreModuleModel.dateTime;
        }
        else
            return "Not available";
    }

    /**
     * Time speed.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.POST, value = "/core/timeSpeed")
    @ResponseStatus( HttpStatus.OK )
    public void timeSpeed(@RequestBody ObjectNode objectNode){
        CoreModuleModel.setTimeSpeed(objectNode.get("timeSpeed").asInt());
    }

    /**
     * Set light.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/core/light")
    @ResponseStatus( HttpStatus.OK )
    public void setLight(@RequestBody ObjectNode objectNode){
        simulationService.setLight(objectNode.get("userLocation").asText(),objectNode.get("privilege").asText(),objectNode.get("location").asText(),objectNode.get("lightOn").asBoolean());
    }

    /**
     * Set door.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/core/door")
    @ResponseStatus( HttpStatus.OK )
    public void setDoor(@RequestBody ObjectNode objectNode){
        simulationService.setDoor(objectNode.get("privilege").asText(),objectNode.get("location").asText(),objectNode.get("doorLock").asText());
    }

    /**
     * Set window.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/core/window")
    @ResponseStatus( HttpStatus.OK )
    public void setWindow(@RequestBody ObjectNode objectNode){
        simulationService.setWindow(objectNode.get("userLocation").asText(),objectNode.get("privilege").asText(),objectNode.get("location").asText(),objectNode.get("windowOpen").asText());
    }

    /**
     * Set auto mode.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.POST, value = "/core/setAutoMode")
    @ResponseStatus( HttpStatus.OK )
    public void setAutoMode(@RequestBody ObjectNode objectNode){
        simulationService.setAutoMode(objectNode.get("autoMode").asBoolean());
    }

}
