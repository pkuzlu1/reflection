package reflection.serDeser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import reflection.util.Logging;

public class Serialize implements SerializationStrategy{
	private String filename;
	private File file;

	/**
	 * 
	 * @param n_filename
	 */
	public Serialize(String n_filename) {
		filename = n_filename;
		//create file if it does not exist or delete the old one 
		file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}
		} catch (IOException e) {
			System.err.println("Cannot Create/Delete file"+filename+ " !");
			e.printStackTrace();
			System.exit(-1);
		}
		Logging.getInstance().write(3,"Serialize Constructor Call");
	}

	/**
	 * @param object
	 */
	public void serializeObject(Object object) {

		FileWriter fileWriter;
		try {
			//base string represents each DPSerialization object
			String base = "";
			base = base.concat("<DPSerialization>\n");
			base = base.concat(" <complexType xsi:type=\""
					+ object.getClass().getName() + "\">\n");

			Method methods[] = object.getClass().getMethods();

			for (int i = 0; i < methods.length; i++) {
				//check if method is getter method
				if (checkGetter(methods[i])) {
					String tag = getTag(methods[i].getName());
					String type = getType(methods[i]);
					Object value = getValue(methods[i], object);
					base = base.concat("  <" + tag + " xsi:type=\"xsd:" + type
							+ "\">" + value + "</" + tag + ">\n");
				}
			}

			base = base.concat(" </complexType>\n");
			base = base.concat("</DPSerialization>\n");

			Logging.getInstance().write(1,base);

			fileWriter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWriter);
			bufferWritter.write(base);
			bufferWritter.flush();
			bufferWritter.close();

		} catch (IOException e) {
			System.err.println("Cannot open file"+filename+" !");
			e.printStackTrace();
			System.exit(-1);
		}

	}

	/**
	 * Checks if given method is getter method or not
	 * @param method
	 * @return true if method is getter otherwise false
	 */
	public boolean checkGetter(Method method) {
		if ((method.getName().startsWith("get"))
				&& (!void.class.equals(method.getReturnType()) && (method
						.getParameterTypes().length == 0))
				&& (!method.getName().equals("getClass"))) {
			return true;
		}
		return false;
	}

	/**
	 * @param methods
	 * @param obj
	 * @return
	 */
	public Object getValue(Method methods, Object obj) {

		try {
			Logging.getInstance().write(2,methods.toString());
			return methods.invoke(obj);
		} catch (IllegalArgumentException e) {
			System.err.println("Illegal Argument!");
			e.printStackTrace();
			System.exit(-1);
		} catch (IllegalAccessException e) {
			System.err.println("Illegal Access!");
			e.printStackTrace();
			System.exit(-1);
		} catch (InvocationTargetException e) {
			System.err.println("Wrong Invocation Target!");
			e.printStackTrace();
			System.exit(-1);
		}
		finally{}
		return null;
	}

	/**
	 * Get return type of the given method.
	 * @param methods
	 * @return return type of the method if method is not recognized returns null
	 */
	public String getType(Method methods) {
		Class<?> retClass = methods.getReturnType();
		if (retClass.equals(Integer.TYPE)) {
			return "int";
		}
		if (retClass.equals(Short.TYPE)) {
			return "short";
		}
		if (retClass.equals(Float.TYPE)) {
			return "float";
		}
		if (retClass.equals(Long.TYPE)) {
			return "long";
		}
		if (retClass.equals(String.class)) {
			return "string";
		}
		if (retClass.equals(Double.TYPE)) {
			return "double";
		}
		if (retClass.equals(Character.TYPE)) {
			return "char";
		}
		return null;
	}

	/**
	 * Gets tag from the method name
	 * @param method_name
	 * @return field name
	 */
	public String getTag(String method_name) {
		int get_length = ("get").length();
		return Character.toLowerCase(method_name.charAt(get_length)) + ""
				+ method_name.substring(get_length + 1);
	}

}
