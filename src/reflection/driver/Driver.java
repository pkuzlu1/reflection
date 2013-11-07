package reflection.driver;

import java.lang.reflect.Method;

import reflection.serDeser.Deserialize;
import reflection.serDeser.Serialize;
import reflection.util.Debug;
import reflection.util.MyAllTypesFirst;

public class Driver {

	public static void main(String[] args) {
		// String InputFileName = args[0];
		// String OutputFileName = args[1];
		// Debug.setDEBUG_VALUE(Integer.valueOf(args[2]));
		Deserialize in_obj= new Deserialize("/home/bsendir1/cs542/DP3/bedri_sendir/reflection/MyAllTypes.txt");	
		Serialize out_obj= new Serialize("/home/bsendir1/cs542/DP3/bedri_sendir/reflection/MySerializedTypes.txt");
		out_obj.apply(in_obj.apply());
		
		Class first1 = MyAllTypesFirst.class;
		Method[] method = first1.getMethods();
		System.out.println(method[5].getName());
		
	}
}
