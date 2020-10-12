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
	
	public static int getTempOutside() 
	{
		return tempOutside;
	}
	
	public static void setTempOutside(int tempOutside) 
	{
		HeatingModuleModel.tempOutside = tempOutside;
	}
	
	public static int getTempInsideGeneral() 
	{
		return tempInsideGeneral;
	}
	
	public static void setTempInsideGeneral(int tempInsideGeneral) 
	{
		HeatingModuleModel.tempInsideGeneral = tempInsideGeneral;
	}
}
