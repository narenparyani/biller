package com.billercrud.billerapi.dao;

import com.billercrud.billerapi.model.Biller;
import com.billercrud.billerapi.model.BillerPayMode;
import com.billercrud.billerapi.model.BillerType;
import com.billercrud.billerapi.model.City;
import com.billercrud.billerapi.model.Email;
import com.billercrud.billerapi.model.PaymentMode;
import com.billercrud.billerapi.model.Phone;
import com.billercrud.billerapi.model.ServiceableCity;
import com.billercrud.billerapi.validation.Validation;

import java.util.*;

import java.sql.*;

/**
 * @author ayush.khandelwal
 *
 */
public class BillerDao {
	// private Connection connection = getOracleConnection();
	public List<Biller> getAllBillers() {
		List<Biller> billers = new ArrayList<>();
		Connection connection = getOracleConnection();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM BILLER ORDER BY BILLERID");
			while (resultSet.next()) {
				Biller biller = new Biller();
				int billerId = resultSet.getInt("BILLERID");
				biller.setBillerId(billerId);
				biller.setBillerName(resultSet.getString("BILLERNAME"));
				biller.setActivationDate(resultSet.getDate("REGISTRATIONDATE"));
				biller.setGst(resultSet.getString("GSTNO"));
				biller.setAddress(resultSet.getString("ADDRESS"));
				biller.setDeactivationDate(resultSet.getDate("DEACTIVATIONDATE"));
				biller.setCity(resultSet.getString("CITY"));
				biller.setDescription(resultSet.getString("DESCRIPTION"));
				biller.setState(resultSet.getString("STATE"));
				biller.setStatus(resultSet.getInt("STATUS"));
				biller.setPhone(getPhoneNumbers(billerId));
				biller.setServiceableCity(getBillerServiceableCityId(billerId));
				biller.setBillerPayMode(getBillerPayMode(billerId));
				//Add code for biller type
				biller.setBillerType(getBillerTypeByBillerId(resultSet.getInt("BILLERTYPEID")));
				//Add code for email
				biller.setEmail(getEmailByBillerId(billerId));
				billers.add(biller);
			}
			statement.close();

		} catch (SQLException exception) {

			exception.printStackTrace();
			return billers;
		} finally {
			closeOracleDriver(connection);
		}
		return billers;
	}

	public Biller getBillerById(int billerId) {
		Biller biller = null;
		Connection connection = getOracleConnection();
		try {
			biller = new Biller();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM BILLER WHERE BILLERID = ?");
			preparedStatement.setInt(1, billerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				biller.setBillerId(billerId);
				biller.setBillerName(resultSet.getString("BILLERNAME"));
				biller.setActivationDate(resultSet.getDate("REGISTRATIONDATE"));
				biller.setGst(resultSet.getString("GSTNO"));
				biller.setAddress(resultSet.getString("ADDRESS"));
				biller.setDeactivationDate(resultSet.getDate("DEACTIVATIONDATE"));
				biller.setCity(resultSet.getString("CITY"));
				biller.setDescription(resultSet.getString("DESCRIPTION"));
				biller.setState(resultSet.getString("STATE"));
				biller.setStatus(resultSet.getInt("STATUS"));
				biller.setPhone(getPhoneNumbers(billerId));
				biller.setServiceableCity(getBillerServiceableCityId(billerId));
				biller.setBillerPayMode(getBillerPayMode(billerId));
				//Add code for biller type
				biller.setBillerType(getBillerTypeByBillerId(resultSet.getInt("BILLERTYPEID")));
				//Add code for email
				biller.setEmail(getEmailByBillerId(billerId));
				preparedStatement.close();
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
			return null;
		} finally {
			closeOracleDriver(connection);
		}
		return biller;
	}

	public Biller getBillerByName(String billerName) {
		Biller biller = null;
		Connection connection = getOracleConnection();
		try {
			biller = new Biller();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BILLER WHERE BILLERNAME = '"+billerName+"'");
			//preparedStatement.setString(1, billerName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				biller = new Biller();
				int billerId = resultSet.getInt("BILLERID");
				biller.setBillerId(billerId);
				biller.setBillerName(resultSet.getString("BILLERNAME"));
				biller.setActivationDate(resultSet.getDate("REGISTRATIONDATE"));
				biller.setGst(resultSet.getString("GSTNO"));
				biller.setAddress(resultSet.getString("ADDRESS"));
				biller.setDeactivationDate(resultSet.getDate("DEACTIVATIONDATE"));
				biller.setCity(resultSet.getString("CITY"));
				// biller.setBillerTypeId(resultSet.getInt(" "));
				biller.setDescription(resultSet.getString("DESCRIPTION"));
				biller.setState(resultSet.getString("STATE"));
				biller.setStatus(resultSet.getInt("STATUS"));
				biller.setPhone(getPhoneNumbers(billerId));
				biller.setServiceableCity(getBillerServiceableCityId(billerId));
				biller.setBillerPayMode(getBillerPayMode(billerId));
				//Add code for biller type
				biller.setBillerType(getBillerTypeByBillerId(resultSet.getInt("BILLERTYPEID")));
				//Add code for email
				biller.setEmail(getEmailByBillerId(billerId));
				preparedStatement.close();
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
			return biller;
		} finally {
			closeOracleDriver(connection);
		}
		return biller;
	}

	public boolean addBiller(Biller biller) {

		String sql = "INSERT INTO BILLER(BILLERNAME, ADDRESS, CITY, STATE, GSTNO, REGISTRATIONDATE, DESCRIPTION, BILLERTYPEID)  VALUES(?,?,?,?,?,?,?,?)";
		Connection connection = getOracleConnection();
		if(Validation.validateBillerName(connection, biller.getBillerName()) && Validation.validateGst(biller.getGst())){
			try {

				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				
				preparedStatement.setString(1, biller.getBillerName());
				preparedStatement.setString(2, biller.getAddress());
				preparedStatement.setString(3, biller.getCity());
				preparedStatement.setString(4, biller.getState());
				preparedStatement.setString(5, biller.getGst());
				preparedStatement.setDate(6, new java.sql.Date(System.currentTimeMillis()));
				preparedStatement.setString(7, biller.getDescription());
				preparedStatement.setInt(8, biller.getBillerType().getBillerTypeId());
				int i = preparedStatement.executeUpdate();
				preparedStatement.close();
				if (i != 0) {
					preparedStatement.close();
					connection.setAutoCommit(false);
					Map<String, String> map = biller.getPhone().getNumber();   //Phone class contains Map of PhoneNo and Department
					Set<String> keySet = map.keySet();
					Statement statement = connection.createStatement();
					for (String phone_no : keySet) {
						if(!Validation.validatePhoneNumber(phone_no)) {
							return false;
						}
						statement.addBatch("INSERT INTO PHONENO VALUES('"+phone_no+"', '"+map.get(phone_no)+"', '"+getBillerByName(biller.getBillerName()).getBillerId()+"')");
					}
					
					Map<String, String> emailMap = biller.getEmail().getEmaiId();   //Email class contains Map of Email and Department
					Set<String> emailKeySet = emailMap.keySet();
					for (String email : emailKeySet) {
						if(!Validation.validateEmail(email)) {
							return false;
						}
						statement.addBatch("INSERT INTO EMAIL VALUES('"+email+"', '"+emailMap.get(email)+"', '"+getBillerByName(biller.getBillerName()).getBillerId()+"')");
					}
					
					for (City city : biller.getServiceableCity().getCity()) {
						
						     statement.addBatch("INSERT INTO SERVICEABLECITY VALUES('" + getBillerByName(biller.getBillerName()).getBillerId()+"', '"+ city.getCityId()+"')");
					};
					System.out.println(biller.getBillerPayMode());
					for (PaymentMode paymentMode : biller.getBillerPayMode().getBillerPaymentMode()) {
					     statement.addBatch("INSERT INTO BILLERPAYMODE VALUES('" + getBillerByName(biller.getBillerName()).getBillerId()+"', '"+ paymentMode.getPaymentModeId()+"')");
				};
					
//					int j[] = statement.executeBatch();
//					for (int j2 : j) {
//						System.out.println(j2);
//					}
					
					connection.commit();
					statement.close();
					return true;
				}
				return false;
			} catch (SQLException exception) {
				exception.printStackTrace();
				return false;
			} finally {
				closeOracleDriver(connection);
			}
		}
		else
			return false;
	}

	public boolean updateAddress(int billerId, String address) {

		Connection connection = getOracleConnection();
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE BILLER SET ADDRESS = ? WHERE BILLERID = ?");
			preparedStatement.setString(1, address);
			preparedStatement.setInt(2, billerId);
			int i = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (i != 0)
				return true;
			else
				return false;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;
		} finally {
			closeOracleDriver(connection);
		}
	}

	public boolean updateCity(int billerId, String city) {
		Connection connection = getOracleConnection();
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE BILLER SET CITY = ? WHERE BILLERID = ?");
			preparedStatement.setString(1, city);
			preparedStatement.setInt(2, billerId);
			int i = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (i != 0)
				return true;
			else
				return false;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;
		} finally {
			closeOracleDriver(connection);
		}

	}

	public boolean updateState(int billerId, String state) {
		Connection connection = getOracleConnection();
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE BILLER SET STATE = ? WHERE BILLERID = ?");
			preparedStatement.setString(1, state);
			preparedStatement.setInt(2, billerId);
			int i = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (i != 0)
				return true;
			else
				return false;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;
		} finally {
			closeOracleDriver(connection);
		}

	}

	

	public boolean updateDescription(int billerId, String description) {
		Connection connection = getOracleConnection();
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE BILLER SET DESCRIPTION = ? WHERE BILLERID = ?");
			preparedStatement.setString(1, description);
			preparedStatement.setInt(2, billerId);
			int i = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (i != 0)
				return true;
			else
				return false;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;
		} finally {
			closeOracleDriver(connection);
		}

	}

	// Work on this
	
	public boolean updateEmailId(int billerId, String oldEmail, String newEmail) {
		if(Validation.validateEmail(newEmail)) {
			if(getOldEmail(billerId, oldEmail)) {
				Connection connection = getOracleConnection();
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("UPDATE EMAIL SET EMAILADDRESS = ? WHERE EMAILADDRESS = ? AND BILLERID = ?");
					preparedStatement.setString(1, newEmail);
					preparedStatement.setString(2, oldEmail);
					preparedStatement.setInt(3, billerId);
					int i = preparedStatement.executeUpdate();
					preparedStatement.close();
					if(i != 0) {
						return true;
					}
					else
						return false;
				}catch(SQLException exception) {
					exception.printStackTrace();
					return false;
				}
				finally {
					closeOracleDriver(connection);
				}
			}
		}
		return false;
	}
	
	private boolean getOldEmail(int billerId, String oldEmail) {
		Connection connection = getOracleConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BILLER B, EMAIL E WHERE B.BILLERID = E.BILLERID AND B.BILLERID = ? AND E.EMAILADDRESS = ?");
			preparedStatement.setInt(1, billerId);
			preparedStatement.setString(2, oldEmail);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				preparedStatement.close();
				return true;
			}
			return false;
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
			return false;
		}
		finally {
			closeOracleDriver(connection);
		}
	}
	
	
	public boolean updatePhoneNumber(int billerId, String oldPhoneNumber, String newPhoneNumber) {
		if(Validation.validatePhoneNumber(newPhoneNumber)) {
			if(getOldPhoneNumber(billerId, oldPhoneNumber)) {
				Connection connection = getOracleConnection();
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PHONENO SET  PHONENUMBER = ? WHERE PHONENUMBER = ? AND BILLERID = ?");
					preparedStatement.setString(1, newPhoneNumber);
					preparedStatement.setString(2, oldPhoneNumber);
					preparedStatement.setInt(3, billerId);
					int i = preparedStatement.executeUpdate();
					preparedStatement.close();
					if(i != 0) {
						return true;
					}
					else
						return false;
				}catch(SQLException exception) {
					exception.printStackTrace();
					return false;
				}
				finally {
					closeOracleDriver(connection);
				}
			}
		}
		
		return false;
	}
	
	private boolean getOldPhoneNumber (int billerId, String oldPhoneNumber) {
		Connection connection = getOracleConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BILLER B, PHONENO P WHERE B.BILLERID = P.BILLERID AND P.BILLERID = ? AND P.PHONENUMBER = ?");
			preparedStatement.setInt(1, billerId);
			preparedStatement.setString(2, oldPhoneNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				preparedStatement.close();
				return true;
			}
			return false;
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
			return false;
		}
		finally {
			closeOracleDriver(connection);
		}
	}
	
	
	

	public boolean deleteBillerById(int billerId) {
		Connection connection = getOracleConnection();
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE BILLER SET STATUS = ?, DEACTIVATIONDATE = ? WHERE BILLERID = ?");
			preparedStatement.setInt(1, 0);
			preparedStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			preparedStatement.setInt(3, billerId);
			int i = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (i == 1)
				return true;
			else
				return false;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;
		} finally {
			closeOracleDriver(connection);
		}

	}

	private Connection getOracleConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
			return null;
		}
		try {
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@10.10.20.3:1781:bduat", "bdtraining",
					"bdtraining123");
			return connection;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return null;
		}
	}

	private boolean closeOracleDriver(Connection connection) {
		try {
			connection.close();
			return true;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;
		}

	}

	public Phone getPhoneNumbers(int billerId) {
		Connection connection = getOracleConnection();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM PHONENO WHERE BILLERID = ?");
			preparedStatement.setInt(1, billerId);
			ResultSet resultSet2 = preparedStatement.executeQuery();
			Map<String, String> map = new HashMap<>();
			while (resultSet2.next()) {
				map.put(resultSet2.getString("PHONENUMBER"), resultSet2.getString("CONTACTDEPARTMENT"));
			}
			Phone phone = new Phone();
			phone.setNumber(map);
			preparedStatement.close();
			return phone;

		} catch (SQLException exception) {
			exception.printStackTrace();
			return null;

		} finally {
			closeOracleDriver(connection);
		}
	}

	public ServiceableCity getBillerServiceableCityId(int billerId) {
		Connection connection = getOracleConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT CI.CITYID, CI.CITYNAME FROM BILLER B, SERVICEABLECITY S, CITY CI WHERE (B.BILLERID = S.BILLERID AND S.CITYID = CI.CITYID AND B.BILLERID = ?)");
			preparedStatement.setInt(1, billerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<City> list = new ArrayList<>();
			while (resultSet.next()) {
				City city = new City();
				city.setCityId(resultSet.getInt(1));
				city.setCityName(resultSet.getString(2));
				list.add(city);
			}
			preparedStatement.close();
			ServiceableCity serviceableCity = new ServiceableCity();
			serviceableCity.setCity(list);
			return serviceableCity;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return null;
		} finally {
			closeOracleDriver(connection);
		}

	}
	
	public List<City> getAllServiceableCity(){
		Connection connection = getOracleConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CITY ORDER BY CITYID");
			ResultSet resultSet = preparedStatement.executeQuery();
			List<City> list = new ArrayList<>();
			while(resultSet.next()) {
				list.add(new City(resultSet.getInt(1), resultSet.getString(2)));
			}
			preparedStatement.close();
			return list;
		}catch(SQLException exception) {
			exception.printStackTrace();
			return null;
		}
		finally {
			closeOracleDriver(connection);
		}
	}
	
	public BillerPayMode getBillerPayMode(int billerId) {
		Connection connection = getOracleConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT PM.PAYMENTMODEID, PM.PAYMENTTYPENAME FROM BILLER B, BILLERPAYMODE BP, PAYMENTMODE PM WHERE (B.BILLERID = BP.BILLERID AND BP.PAYMENTMODEID = PM.PAYMENTMODEID AND B.BILLERID = ?)");
			preparedStatement.setInt(1, billerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<PaymentMode> list = new ArrayList<>();
			while (resultSet.next()) {
				PaymentMode paymentMode = new PaymentMode();
				paymentMode.setPaymentModeId(resultSet.getInt(1));
				paymentMode.setPaymentTypeName(resultSet.getString(2));
				list.add(paymentMode);
			}
			preparedStatement.close();
			BillerPayMode billerPayMode = new BillerPayMode();
			billerPayMode.setBillerPaymentMode(list);
			return billerPayMode;
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return null;
		}
		finally {
			closeOracleDriver(connection);
		}

	}
	private BillerType getBillerTypeByBillerId(int billerTypeId) {
		Connection connection = getOracleConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BILLERTYPE WHERE BILLERTYPEID= ? ");
			preparedStatement.setInt(1, billerTypeId);
			ResultSet resultSet= preparedStatement.executeQuery();
			BillerType billerType = new BillerType();
			while (resultSet.next())
			{
				
				billerType.setBillerTypeId(billerTypeId);
				billerType.setBillerCategoryName(resultSet.getString("BILLERCATEGORYNAME"));
				billerType.setDescription(resultSet.getString("DESCRIPTIONOFBILLERTYPE"));
			}
			preparedStatement.close();
			return billerType;
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
			return null;
		}
		finally {
			closeOracleDriver(connection);
		}
	}
	public Email getEmailByBillerId(int billerId) {
		Connection connection = getOracleConnection();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM EMAIL WHERE BILLERID = ?");
			preparedStatement.setInt(1, billerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			Map<String, String> map = new HashMap<>();
			while (resultSet.next()) {
				map.put(resultSet.getString("EMAILADDRESS"), resultSet.getString("EMAILDEPARTMENT"));
			}
			Email email = new Email();
			email.setEmaiId(map);
			preparedStatement.close();
			return email;

		} catch (SQLException exception) {
			exception.printStackTrace();
			return null;

		} finally {
			closeOracleDriver(connection);
		}
	}
}



