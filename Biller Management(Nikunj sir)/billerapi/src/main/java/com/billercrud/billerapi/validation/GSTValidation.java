package com.billercrud.billerapi.validation;

import java.util.regex.Pattern;

public class GSTValidation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String GST="11AABCI6363G1ZQ";
       
            final Pattern GST_REGEX = Pattern.compile("\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}");
            boolean a= GST_REGEX.matcher(GST).matches();
            
            if(a) {
               System.out.println("valid GST");
            }
            else {
               System.out.println("invalid GST");
            }


	}

}
