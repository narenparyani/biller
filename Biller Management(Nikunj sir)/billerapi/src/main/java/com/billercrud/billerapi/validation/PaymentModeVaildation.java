package com.billercrud.billerapi.validation;

import java.util.ArrayList;

public class PaymentModeVaildation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int flag=0;
		   String str1 = "watr";  
	        ArrayList<String> list = new ArrayList<>();  
	        list.add("Debit card");   
	        list.add("Wallets");  
	        list.add("Credit Card");  
	        list.add("NEFT");  
	        list.add("UPI");  
	        for (String str : list) {  
	            if (str.equalsIgnoreCase(str1)) {  
	                //System.out.println(str1+" is the right option");  
	            	flag=1;
	            	break;
	            }  
	            else {
	            	flag=0;
	            	//System.out.println("Please select the right option");
	            }
	        }
	        if(flag==1)
	        {
                System.out.println(str1+" is the right option");  

	        }
	        else
	        {
            	System.out.println("Please select the right option");

	        }

	}

}
