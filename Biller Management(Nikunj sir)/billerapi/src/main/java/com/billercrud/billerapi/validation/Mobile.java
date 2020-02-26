package com.billercrud.billerapi.validation;

import java.util.regex.Pattern;

public class Mobile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String phone="+919890733080";
        //"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
        //"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE
        
            final Pattern PHONE_REGEX = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$");
            boolean a= PHONE_REGEX.matcher(phone).matches();
            
            if(a) {
               System.out.println("valid number");
            }
            else {
               System.out.println("invalid number");
            }


	}

}
