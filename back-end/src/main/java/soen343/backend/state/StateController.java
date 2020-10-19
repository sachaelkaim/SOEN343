package soen343.backend.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*") //to unblock request to/from react
@RequestMapping("api/")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/state")
	public void changeState(@RequestBody State state) {
		stateService.updateState(state);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/state")
	public State simulationState() {
		return stateService.getState();
	}
	
	
}
