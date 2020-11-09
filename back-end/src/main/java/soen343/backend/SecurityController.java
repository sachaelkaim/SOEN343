package soen343.backend;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The type Security controller.
 */
@RestController
@CrossOrigin("*") //to unblock request to/from react
@RequestMapping("api/")
public class SecurityController {

    @Autowired
    private SimulationService simulationService;

    /**
     * Sets away mode.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.POST, value = "/security/setAwayMode")
    @ResponseStatus( HttpStatus.OK )
    public void setAwayMode(@RequestBody ObjectNode objectNode) {
        simulationService.setAwayMode(objectNode.get("awayMode").asBoolean(), objectNode.get("userPrivilege").asText());
    }

    /**
     * Notify authorities time.
     *
     * @param objectNode the object node
     */
    @RequestMapping(method = RequestMethod.POST, value = "/security/notifyAuthoritiesTime")
    @ResponseStatus( HttpStatus.OK )
    public void notifyAuthoritiesTime(@RequestBody ObjectNode objectNode) {
        SecurityModuleModel.setTimeCallAuthorities(objectNode.get("timeAuthorities").asInt());
    }

}
