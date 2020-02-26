package com.billercrud.billerapi.validation;

import java.util.regex.Pattern;

public class DateValidation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		


		
		String date="2016/02/04";
        
        
            final Pattern DATE_REGEX = Pattern.compile("^(?:[0-9][0-9])?[0-9][0-9]/[0-3][0-9]/[0-3][0-9]$");
            boolean a= DATE_REGEX.matcher(date).matches();
            
            if(a) {
               System.out.println("Valid Date");
            }
            else {
               System.out.println("Invalid Date");
            }


	}

}

