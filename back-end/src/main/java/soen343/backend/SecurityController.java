package soen343.backend;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*") //to unblock request to/from react
@RequestMapping("api/")
public class SecurityController {

    @Autowired
    private SimulationService simulationService;

    @RequestMapping(method = RequestMethod.POST, value = "/security/setAwayMode")
    public void setAwayMode(@RequestBody ObjectNode objectNode) {
        simulationService.setAwayMode(objectNode.get("awayMode").asBoolean());
    }

}
