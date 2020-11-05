package soen343.backend;


import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000") //to unblock request to/from react
@RequestMapping("api/")
public class CoreModuleController {

    @Autowired
    private SimulationService simulationService;

    @RequestMapping(method = RequestMethod.GET, value = "/core/dateAndTime")
    @ResponseStatus( HttpStatus.OK )
    public void getDateAndTime() {
        simulationService.getDateAndTime();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/core/light")
    @ResponseStatus( HttpStatus.OK )
    public void setLight(@RequestBody ObjectNode objectNode){
        simulationService.setLight(objectNode.get("userLocation").asText(),objectNode.get("privilege").asText(),objectNode.get("location").asText(),objectNode.get("lightOn").asBoolean());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/core/door")
    @ResponseStatus( HttpStatus.OK )
    public void setDoor(@RequestBody ObjectNode objectNode){
        simulationService.setDoor(objectNode.get("privilege").asText(),objectNode.get("location").asText(),objectNode.get("doorLock").asText());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/core/window")
    @ResponseStatus( HttpStatus.OK )
    public void setWindow(@RequestBody ObjectNode objectNode){
        simulationService.setWindow(objectNode.get("userLocation").asText(),objectNode.get("privilege").asText(),objectNode.get("location").asText(),objectNode.get("windowOpen").asText());
    }

}
