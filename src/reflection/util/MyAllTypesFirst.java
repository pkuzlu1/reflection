package reflection.util;

public class MyAllTypesFirst {
	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	public double getMyDouble() {
		return myDouble;
	}

	public void setMyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

	public long getMyLong() {
		return myLong;
	}

	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}

	public char getMyChar() {
		return myChar;
	}

	public void setMyChar(char myChar) {
		this.myChar = myChar;
	}

	private int myInt;
	private String myString;
	private double myDouble;
	private long myLong;
	private char myChar;

	@Override
	public int hashCode() {
		final int prime = 31;
		int code = 1;
		int temp = 0;
		if (myString != null) {
			myString.hashCode();
		}
		code = prime * code + temp;
		code = prime * code + Double.valueOf(myDouble).hashCode();
		code = prime * code + Long.valueOf(myLong).hashCode();
		code = prime * code + myChar;
		code = prime * code + myInt;

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
		MyAllTypesFirst other_object = (MyAllTypesFirst) obj;
		if (myChar != other_object.myChar) {
			return false;
		}
		else if (!Double.valueOf(myDouble).equals(Double.valueOf(other_object.myDouble))) {
			return false;
		}
		else if (myInt != other_object.myInt) {
			return false;
		}
		else if (myLong != other_object.myLong) {
			return false;
		}
		if (myString == null) {
			if (other_object.myString != null){
				return false;
			}
		} else if (!myString.equals(other_object.myString)){
			return false;
		}
		
		return true;
	}
}
