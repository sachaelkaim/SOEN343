package soen343.backend;

/**
 * The type Heating module model.
 */
public class HeatingModuleModel {
	//Static to be accessed anywhere
	private static int tempOutside;
	private static int tempInsideGeneral;

    /**
     * Instantiates a new Heating module model.
     */
    HeatingModuleModel()
	{
		HeatingModuleModel.tempOutside = 0;
		HeatingModuleModel.tempInsideGeneral = 0;
	}

    /**
     * Instantiates a new Heating module model.
     *
     * @param tempOutside       the temp outside
     * @param tempInsideGeneral the temp inside general
     */
    HeatingModuleModel(int tempOutside, int tempInsideGeneral)
	{
		HeatingModuleModel.tempOutside = tempOutside;
		HeatingModuleModel.tempInsideGeneral = tempInsideGeneral;
	}

    /**
     * Gets temp outside.
     *
     * @return the temp outside
     */
    public static int getTempOutside()
	{
		return tempOutside;
	}

    /**
     * Sets temp outside.
     *
     * @param tempOutside the temp outside
     */
    public static void setTempOutside(int tempOutside)
	{
		HeatingModuleModel.tempOutside = tempOutside;
	}

    /**
     * Gets temp inside general.
     *
     * @return the temp inside general
     */
    public static int getTempInsideGeneral()
	{
		return tempInsideGeneral;
	}

    /**
     * Sets temp inside general.
     *
     * @param tempInsideGeneral the temp inside general
     */
    public static void setTempInsideGeneral(int tempInsideGeneral)
	{
		HeatingModuleModel.tempInsideGeneral = tempInsideGeneral;
	}
}
