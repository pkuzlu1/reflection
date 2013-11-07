package reflection.serDeser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Deserialize {
	private String filename;

	public Deserialize(String n_filename) {
		filename = n_filename;
	}

	public ArrayList<Object> apply() {
		BufferedReader br = null;
		ArrayList<Object> objects = new ArrayList<Object>();

		String inputLine = null;
		try {
			br = new BufferedReader(new FileReader(filename));

			int object_counter = 0;
			Object temp=null;
			while ((inputLine = br.readLine()) != null) {
				if (inputLine.contains("<complexType ")) {
					Class<?> c = Class.forName("reflection.util.MyAllTypesFirst");
					temp=c.newInstance();
					

				}
				if (inputLine.contains("</complexType>")) {
					objects.add(temp);
					temp=null;
					object_counter++;
				}
				System.out.println(inputLine);
			}
		} catch (FileNotFoundException e) {
			System.err.println("No Input File Found!");
		} catch (IOException e) {
			System.err.println("Error Reading File!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.err.println("Cannot Close the Stream!");
			}
		}
		return null;

	}
}
