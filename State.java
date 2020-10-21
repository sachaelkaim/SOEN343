package soen343.backend.state;


public class State {
	private boolean isOn;

	public State() {
		isOn = false;
	}

	public boolean isOn() {
		return isOn;
	}
    /**
     * Controls on/off depending on the boolean that is passed
     * @param isOn
     */
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	
}
