package com.billercrud.billerapi.model;

import java.sql.Date;

public class Biller {
	private int billerId;
	private String billerName;

	public String getBillerName() {
		return billerName;
	}

	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}


	// private int billerTypeId;
	private String address;
	private String gst;
	private int status;
	private Date deactivationDate;
	private Date activationDate;
	private String city;
	private String state;
	private String description;
	private Phone phone;
	private ServiceableCity serviceableCity;
	private Email email;
	private BillerPayMode billerPayMode;
	private BillerType billerType;

	public BillerType getBillerType() {
		return billerType;
	}

	public void setBillerType(BillerType billerType) {
		this.billerType = billerType;
	}

	public BillerPayMode getBillerPayMode() {
		return billerPayMode;
	}

	public void setBillerPayMode(BillerPayMode billerPayMode) {
		this.billerPayMode = billerPayMode;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public ServiceableCity getServiceableCity() {
		return serviceableCity;
	}

	public void setServiceableCity(ServiceableCity serviceableCity) {
		this.serviceableCity = serviceableCity;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getBillerId() {
		return billerId;
	}

	public void setBillerId(int billerId) {
		this.billerId = billerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDeactivationDate() {
		return deactivationDate;
	}

	public void setDeactivationDate(Date registrationDate) {
		this.deactivationDate = registrationDate;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date string) {
		this.activationDate = string;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Biller [billerId=" + billerId + ", billerName=" + billerName + ", address=" + address + ", gst=" + gst
				+ ", status=" + status + ", deactivationDate=" + deactivationDate + ", activationDate=" + activationDate
				+ ", city=" + city + ", state=" + state + ", description=" + description + ", phone=" + phone
				+ ", serviceableCity=" + serviceableCity + ", email=" + email + ", billerPayMode=" + billerPayMode
				+ ", billerType=" + billerType + "]";
	}
}
