package soen343.backend.state;

import org.springframework.stereotype.Service;

@Service
public class StateService {
	
	private State state = new State();
	
	
	public State getState() {
		return state;
	}
	
	public void updateState(State state) {
		this.state.setOn(state.isOn());
	}
}
