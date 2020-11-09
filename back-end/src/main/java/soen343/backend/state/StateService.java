package soen343.backend.state;

import org.springframework.stereotype.Service;

/**
 * The type State service.
 */
@Service
public class StateService {
	
	private State state = new State();

    /**
     * Gets state.
     *
     * @return the state
     */
    public State getState() {
		return state;
	}

    /**
     * Update state.
     *
     * @param state the state
     */
    public void updateState(State state) {
		this.state.setOn(state.isOn());
	}

    /**
     * Gets current state.
     *
     * @return the current state
     */
    public boolean getCurrentState() {
		return state.isOn();
	}
}
