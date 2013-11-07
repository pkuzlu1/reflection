package reflection.serDeser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Serialize {
	private String filename;

	public Serialize(String n_filename) {
		filename = n_filename;
	}

	public void apply(ArrayList<Object> objects) {
	}
}
