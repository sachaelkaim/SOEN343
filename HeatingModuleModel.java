package soen343.backend;

public class HeatingModuleModel {
	//Static to be accessed anywhere
	private static int tempOutside;
	private static int tempInsideGeneral;
	
	HeatingModuleModel()
	{
		HeatingModuleModel.tempOutside = 0;
		HeatingModuleModel.tempInsideGeneral = 0;
	}
	
	HeatingModuleModel(int tempOutside, int tempInsideGeneral)
	{
		HeatingModuleModel.tempOutside = tempOutside;
		HeatingModuleModel.tempInsideGeneral = tempInsideGeneral;
	}
	/**
	 * 
	 * @return Outside Temperature
	 */
	public static int getTempOutside() 
	{
		return tempOutside;
	}
	/**
	 * 
	 * @param tempOutside
	 */
	public static void setTempOutside(int tempOutside) 
	{
		HeatingModuleModel.tempOutside = tempOutside;
	}
	/**
	 * 
	 * @return Inside Temperature
	 */
	public static int getTempInsideGeneral() 
	{
		return tempInsideGeneral;
	}
	/**
	 * 
	 * @param tempInsideGeneral
	 */
	public static void setTempInsideGeneral(int tempInsideGeneral) 
	{
		HeatingModuleModel.tempInsideGeneral = tempInsideGeneral;
	}
}
