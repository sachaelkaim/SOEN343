package soen343.backend;

public class SecurityModuleModel {
	//Static to be accessed anywhere
	private static boolean awayMode;
	SecurityModuleModel()
	{
		setAwayMode(false);
	}
	public static boolean isAwayMode() {
		return awayMode;
	}
	/**
	 * Sets a Security mode for when there is no user at home
	 * @param awayMode
	 */
	public static void setAwayMode(boolean awayMode) {
		SecurityModuleModel.awayMode = awayMode;
	}
}
