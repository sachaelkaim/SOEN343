package soen343.backend.modules;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soen343.backend.state.State;
import soen343.backend.state.StateService;

@RestController
@CrossOrigin("*") //to unblock request to/from react
@RequestMapping("api/")
public class securityModuleController {

    @Autowired
    private SimulationMasterService masterService;

    @RequestMapping(method = RequestMethod.POST, value = "/security/setAwayMode/")
    public void setAwayMode(@RequestBody ObjectNode objectNode) {
        masterService.setAwayMode(objectNode.get("awayMode").asBoolean());
    }

}
