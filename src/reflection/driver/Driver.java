package reflection.driver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import reflection.serDeser.Deserialize;
import reflection.serDeser.Serialize;
import reflection.util.Debug;
import reflection.util.MyAllTypesFirst;
import reflection.util.MyAllTypesSecond;

public class Driver {

	public static void main(String[] args) {
		String inputFileName = args[0];
		String outputFileName = args[1];
		Debug.setDEBUG_VALUE(Integer.valueOf(args[2]));

		Deserialize in_obj = new Deserialize(inputFileName);
		Serialize out_obj = new Serialize(outputFileName);
		ArrayList<Object> deSerializedObjects = in_obj.deSerializeFile();

		if (deSerializedObjects != null) {
			Set<MyAllTypesFirst> fst = new HashSet<MyAllTypesFirst>();
			Set<MyAllTypesSecond> snd = new HashSet<MyAllTypesSecond>();

			for (int i = 0; i < deSerializedObjects.size(); i++) {

				if (deSerializedObjects.get(i).getClass().getName()
						.equals(MyAllTypesFirst.class.getName())) {
					fst.add((MyAllTypesFirst) deSerializedObjects.get(i));
				}
				if (deSerializedObjects.get(i).getClass().getName()
						.equals(MyAllTypesSecond.class.getName())) {
					snd.add((MyAllTypesSecond) deSerializedObjects.get(i));
				}
				out_obj.serializeObject(deSerializedObjects.get(i));
			}

			System.out.println("Unique MyAllTypesFirst=" + fst.size());
			System.out.println("Unique MyAllTypesSecond=" + snd.size());

		} else {
			System.err.println("");
		}
	}
}
