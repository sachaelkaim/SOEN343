package soen343.backend.state;

import org.springframework.stereotype.Service;

@Service
public class StateService {
	
	private State state = new State();
     /**
      * 
      * @return state
      */
	public State getState() {
		return state;
	}
	/**
	 * updates to state parameter that is passed 
	 * @param state
	 */
	public void updateState(State state) {
		this.state.setOn(state.isOn());
	}
}
