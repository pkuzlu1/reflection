package reflection.serDeser;

import java.util.ArrayList;

public interface DeSerializationStrategy {
	ArrayList<Object> deSerialize(String filename);
}
