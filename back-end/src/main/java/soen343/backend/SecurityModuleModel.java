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
	public static void setAwayMode(boolean awayMode) {
		SecurityModuleModel.awayMode = awayMode;
	}
}
