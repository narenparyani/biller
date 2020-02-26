package com.billercrud.billerapi.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Validation {

	public static boolean validateGst(String gst) {
		final Pattern GST_REGEX = Pattern.compile("\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}");
        return GST_REGEX.matcher(gst).matches();
    }
	
	public static boolean validatePhoneNumber(String phoneNumber) {
		final Pattern PHONE_REGEX = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$");
        return PHONE_REGEX.matcher(phoneNumber).matches();
	}
	public static boolean validateBillerName(Connection connection,String billerName) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BILLER WHERE BILLERNAME = ?");
			preparedStatement.setString(1, billerName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return false;
			}
			else
				return true;
			
		}catch(SQLException exception) {
			exception.printStackTrace();
			return false;
		}
	}
	public static boolean validateEmail(String email) {
		final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9.]+@[A-Z0-9]+\\.[A-Z]{2,3}$", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
	}
	
	public static boolean validate(Connection connection, String billerName, String gst,String email, String phoneNumber) {
		if(validateBillerName(connection, billerName)) {
			if(validateGst(gst)) {
				if(validateEmail(email)) {
					if(validatePhoneNumber(phoneNumber)) {
						return true;
					}
					return false;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	
}
