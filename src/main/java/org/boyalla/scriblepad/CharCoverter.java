package org.boyalla.scriblepad;

public class CharCoverter {
	
	public static void main(String[] args) {
		
		String example = "This is an example";
	    byte[] bytes = example.getBytes();

	    System.out.println("Text : " + example);
	    System.out.println("Text [Byte Format] : " + bytes);
	    System.out.println("Text [Byte Format] : " + bytes.toString());

	    String s = new String(bytes);
	    System.out.println("Text Decryted : " + s);
	    
		 
		byte i = 4;
		char ch = (char)i;
		System.out.println(Character.toString(ch)); // Prints '4'
	}
	

}
