package reflection.util;

public class Logging {

	public enum LogLevels {
		NO_OUTPUT, DRIVER_OUTPUT, QUICK_AGENDA, ALL_ACTIVITIES, CONSTRUCTOR_CALL;

	}

	private int debug_value;

	private static Logging instance = null;

	private Logging() {
		setDebug_value(Debug.getDEBUG_VALUE());
	}

	/**
	 * @return the unique instance of Logging object
	 */
	public static Logging getInstance() {
		if (instance == null) {
			instance = new Logging();
		}
		return instance;
	}

	/**
	 * Writes message to the logger
	 * 
	 * @param debug_level
	 * @param message
	 */
	public void write(int debug_level, String message) {
		if (debug_level == Debug.getDEBUG_VALUE()) {
			switch (LogLevels.values()[debug_level]) {

			case NO_OUTPUT: {
				System.out.println("Unique Objects");
				System.out.println(message);
				break;
			}
			case DRIVER_OUTPUT: {
				System.out.println("Driver Output");
				System.out.println(message);
				break;
			}
			case QUICK_AGENDA: {
				System.out.println("Quick Agenda:\n" + message);
				break;
			}
			case ALL_ACTIVITIES: {
				System.out.println("ALL ACTIVITES IN CONFERENCE :");
				System.out.println(message);
				break;
			}
			case CONSTRUCTOR_CALL: {
				System.out.println("Constructor CALL: " + message);
				break;
			}

			default: {
				System.out.println("default");
				break;
			}

			}
		}

	}

	/**
	 * @return logger debug value
	 */
	public int getDebug_value() {
		return debug_value;
	}

	/**
	 * @param debug_value
	 *            sets logger debug value
	 */
	public void setDebug_value(int debug_value) {
		this.debug_value = debug_value;
	}
}