package reflection.serDeser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

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

		
			Object temp=null;
			Class<?> c = null;
			while ((inputLine = br.readLine()) != null) {
				if(inputLine.contains("DPSerialization>")){}
				else if (inputLine.contains("<complexType ")) {
					//read type
				
					int classname_start=inputLine.indexOf("\"");
					int classname_end=inputLine.indexOf("\"",classname_start+1);
					//System.out.println(classname_start+" "+classname_end);
					
					String classname=inputLine.substring(classname_start+1,classname_end);
					c = Class.forName(classname);
					System.out.println(classname);
					temp=c.newInstance();
				}	
				else if (inputLine.contains("</complexType>")) {
					objects.add(temp);
					temp=null;
					
				}
				else{
					if(inputLine.length()>0){
						//read tag			
						int tag_start=inputLine.indexOf("<");
						int tag_end=inputLine.indexOf(" ",tag_start);
						String tag=Character.toUpperCase(inputLine.charAt(tag_start+1))+inputLine.substring(tag_start+2,tag_end);
						//System.out.println(tag);
						
						//read type
						int type_start=inputLine.indexOf(":",inputLine.indexOf("\"")); 
						int type_end=inputLine.indexOf("\"",type_start);
						String type= inputLine.substring(type_start+1,type_end);
						//System.out.println(type);
						
						//read value
						int value_start=inputLine.indexOf(">");
						int value_end=inputLine.indexOf("<", value_start);
						String value=inputLine.substring(value_start+1,value_end);
						//System.out.println(value);
						
						/*
						Method[] method = c.getDeclaredMethods();
						for(int i=0;i<method.length;i++){
							System.out.println(method[i].toString());	
						}
						*/
						if(type.equals("int")){
							Method mymethod=c.getDeclaredMethod("set"+tag,new Class[]{Integer.TYPE});
							mymethod.invoke(temp,(Integer.valueOf(value)));
						}
						else if(type.equals("string")){
							Method mymethod=c.getDeclaredMethod("set"+tag,new Class[]{String.class});
							mymethod.invoke(temp,value);
						}
						else if(type.equals("short")){
							Method mymethod=c.getDeclaredMethod("set"+tag,new Class[]{Short.TYPE});
							mymethod.invoke(temp,(Short.valueOf(value)));
						}
						else if(type.equals("double")){
							Method mymethod=c.getDeclaredMethod("set"+tag,new Class[]{Double.TYPE});
							mymethod.invoke(temp,(Double.valueOf(value)));
						}
						else if(type.equals("long")){
							Method mymethod=c.getDeclaredMethod("set"+tag,new Class[]{Long.TYPE});
							mymethod.invoke(temp,(Long.valueOf(value)));
						}
						else if(type.equals("char")){
							Method mymethod=c.getDeclaredMethod("set"+tag,new Class[]{Character.TYPE});
							mymethod.invoke(temp,(value.charAt(0)));
						}
						else if(type.equals("float")){
							Method mymethod=c.getDeclaredMethod("set"+tag,new Class[]{Float.TYPE});
							mymethod.invoke(temp,(Float.valueOf(value)));
						}
					}
				}
				//System.out.println(inputLine);
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
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.err.println("Cannot Close the Stream!");
			}
		}
		System.out.println("Total Number of objects: "+objects.size());
		return objects;

	}
}
