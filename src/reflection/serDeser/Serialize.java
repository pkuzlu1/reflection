package reflection.serDeser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
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
			base=base.concat("<DPSerialization>\n");
			base=base.concat(" <complexType xsi:type=\"" + object.getClass().getName() + "\">\n");
			Field fields[]=object.getClass().getFields();
			
			base=base.concat(" </complexType>\n");
			base=base.concat("</DPSerialization>\n");
			System.out.println(base);
			
			fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			//bufferWritter.write(base);
			bufferWritter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
