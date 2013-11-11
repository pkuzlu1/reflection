package reflection.serDeser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Serialize {
	private String filename;
	private File file;

	public Serialize(String n_filename) {
		filename = n_filename;
		file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void serializeObject(Object object) {

		FileWriter fileWritter;
		try {
			String base = "";
			base = base.concat("<DPSerialization>\n");
			base = base.concat(" <complexType xsi:type=\""
					+ object.getClass().getName() + "\">\n");
			
			Method methods[] = object.getClass().getMethods();

			for (int i = 0; i < methods.length; i++) {
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

			System.out.println(base);

			fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(base);
			bufferWritter.flush();
			bufferWritter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean checkGetter(Method method) {
		if ((method.getName().startsWith("get"))
				&& (!void.class.equals(method.getReturnType()) && (method
						.getParameterTypes().length == 0))
				&& (!method.getName().equals("getClass"))) {
			return true;
		}
		return false;
	}

	public Object getValue(Method methods, Object obj) {

		try {
			return methods.invoke(obj);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

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

	public String getTag(String method_name) {
		int get_length = ("get").length();
		return Character.toLowerCase(method_name.charAt(get_length)) + ""
				+ method_name.substring(get_length + 1);
	}

}
