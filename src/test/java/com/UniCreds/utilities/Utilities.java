package com.UniCreds.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class Utilities {
	
	public static String generateFullName(int length) {
		String name = RandomStringUtils.randomAlphabetic(length);
        return name;
	}
	
	 public static String generateEmail(int length){
		 
		 String randomText = RandomStringUtils.randomAlphabetic(length);
		 String lowerCase = randomText.toLowerCase();
	        String email =  lowerCase;
	        return email;
	    }

    public static String generatePhoneNumber(int length){
        String number = RandomStringUtils.randomNumeric(length);
        return number;
    }

    public static String generateMessage(int length){
        String message = RandomStringUtils.randomAlphabetic(length);
        return message;
    }

}
