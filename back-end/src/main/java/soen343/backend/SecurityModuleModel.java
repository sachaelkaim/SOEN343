package soen343.backend;

/**
 * The type Security module model.
 */
public class SecurityModuleModel {
    /**
     * The constant awayMode.
     */
//Static to be accessed anywhere
	public static boolean awayMode;
    /**
     * The constant timeCallAuthorities.
     */
    public static int timeCallAuthorities = 1;

    /**
     * Instantiates a new Security module model.
     */
    SecurityModuleModel()
	{
		setAwayMode(false);
	}

    /**
     * Is away mode boolean.
     *
     * @return the boolean
     */
    public static boolean isAwayMode() {
		return awayMode;
	}

    /**
     * Sets away mode.
     *
     * @param awayMode the away mode
     */
    public static void setAwayMode(boolean awayMode) {
		SecurityModuleModel.awayMode = awayMode;
	}

    /**
     * Gets time call authorities.
     *
     * @return the time call authorities
     */
    public static int getTimeCallAuthorities() {
		return timeCallAuthorities;
	}

    /**
     * Sets time call authorities.
     *
     * @param timeAuthorities the time authorities
     */
    public static void setTimeCallAuthorities(int timeAuthorities) {
		SecurityModuleModel.timeCallAuthorities = timeAuthorities;
	}
}
