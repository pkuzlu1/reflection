package reflection.driver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import reflection.util.Debug;
import reflection.util.Logging;
import reflection.util.MyAllTypesFirst;
import reflection.util.MyAllTypesSecond;
import reflection.serDeser.DeSerializationStrategy;
import reflection.serDeser.Deserialize;
import reflection.serDeser.SerializationStrategy;
import reflection.serDeser.Serialize;


public class Driver {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err
					.println("Wrong Number of Arguments ! \n Usage: serDeSer <inputFilename> <outputFilename> <debugvalue>");
			System.exit(-1);
		}
		try {
			Debug.setDEBUG_VALUE(Integer.parseInt(args[2]));
		} catch (NumberFormatException e) {
			System.err.println("Debug value must be integer!");
			System.exit(-1);
		} finally {
		}

		String inputFileName = args[0];
		String outputFileName = args[1];

		DeSerializationStrategy deSerializer = new Deserialize();
		SerializationStrategy Serializer = new Serialize(outputFileName);

		ArrayList<Object> deSerializedObjects = deserialize(inputFileName,deSerializer);
		if (deSerializedObjects != null){
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

				serialize(deSerializedObjects.get(i),Serializer);
			}
			Logging.getInstance().write(0,
					"Unique MyAllTypesFirst=" + fst.size());
			Logging.getInstance().write(0,
					"Unique MyAllTypesSecond=" + snd.size());

		} else {
			System.err.println("No deSerialized Object Found! ");
			System.exit(-1);
		}
	}

	public static void serialize(Object object, SerializationStrategy serializer){
		serializer.serializeObject(object);
	}
	public static ArrayList<Object> deserialize(String filename, DeSerializationStrategy deserializer){
		return deserializer.deSerialize(filename);
	}
}
