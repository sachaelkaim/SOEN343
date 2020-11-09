package soen343.backend.state;


/**
 * The type State.
 */
public class State {
	private boolean isOn;

    /**
     * Instantiates a new State.
     */
    public State() {
		isOn = false;
	}

    /**
     * Is on boolean.
     *
     * @return the boolean
     */
    public boolean isOn() {
		return isOn;
	}

    /**
     * Sets on.
     *
     * @param isOn the is on
     */
    public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	
}
