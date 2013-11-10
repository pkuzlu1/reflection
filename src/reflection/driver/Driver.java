package reflection.driver;

import java.lang.reflect.Method;
import java.util.ArrayList;

import reflection.serDeser.Deserialize;
import reflection.serDeser.Serialize;
import reflection.util.Debug;
import reflection.util.MyAllTypesFirst;

public class Driver {

	public static void main(String[] args) {
		// String InputFileName = args[0];
		// String OutputFileName = args[1];
		// Debug.setDEBUG_VALUE(Integer.valueOf(args[2]));
		Deserialize in_obj= new Deserialize("/home/bedri/DPP3/reflection/MyAllTypes.txt");	
		Serialize out_obj= new Serialize("/home/bedri/DPP3/reflection/MySerializedTypes.txt");
		ArrayList<Object> deSerializedObjects=in_obj.apply();
		
		for(int i=0;i<deSerializedObjects.size();i++){
			System.out.println("in");
			out_obj.serializeObject(deSerializedObjects.get(i));
		}
		
		System.out.println("");
	}
}
