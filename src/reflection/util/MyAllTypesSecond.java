package reflection.util;

public class MyAllTypesSecond {
	public int getMyIntS() {
		return myIntS;
	}

	public void setMyIntS(int myIntS) {
		this.myIntS = myIntS;
	}

	public String getMyStringS() {
		return myStringS;
	}

	public void setMyStringS(String myStringS) {
		this.myStringS = myStringS;
	}

	public float getMyFloatS() {
		return myFloatS;
	}

	public void setMyFloatS(float myFloatS) {
		this.myFloatS = myFloatS;
	}

	public short getMyShortS() {
		return myShortS;
	}

	public void setMyShortS(short myShortS) {
		this.myShortS = myShortS;
	}

	public char getMyCharS() {
		return myCharS;
	}

	public void setMyCharS(char myCharS) {
		this.myCharS = myCharS;
	}

	private int myIntS;
	private String myStringS;
	private float myFloatS;
	private short myShortS;
	private char myCharS;

	@Override
	public int hashCode() {
		final int prime = 31;
		int code = 1;
		int temp = 0;
		if (myStringS != null) {
			myStringS.hashCode();
		}
		code = prime * code + temp;
		code = prime * code + Float.valueOf(myFloatS).hashCode();
		code = prime * code + myCharS;
		code = prime * code + myIntS;
		code = prime * code + myShortS;
		return code;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		}

		MyAllTypesSecond other_object = (MyAllTypesSecond) obj;
		if (myCharS != other_object.myCharS) {
			return false;
		} else if (!Float.valueOf(myFloatS).equals(
				Float.valueOf(other_object.myFloatS))) {
			return false;
		} else if (myIntS != other_object.myIntS) {
			return false;
		} else if (myShortS != other_object.myShortS) {
			return false;
		}
		if (myStringS == null) {
			if (other_object.myStringS != null) {
				return false;
			}
		} else if (!myStringS.equals(other_object.myStringS)) {
			return false;
		}
		return true;
	}
}
