package soen343.backend;

public class SecurityModuleModel {
	//Static to be accessed anywhere
	public static boolean awayMode;
	public static int timeCallAuthorities = 1;
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
	public static int getTimeCallAuthorities() {
		return timeCallAuthorities;
	}

	public static void setTimeCallAuthorities(int timeAuthorities) {
		SecurityModuleModel.timeCallAuthorities = timeAuthorities;
	}
}
