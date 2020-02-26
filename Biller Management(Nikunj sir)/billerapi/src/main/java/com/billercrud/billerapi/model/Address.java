package com.billercrud.billerapi.model;

public class Address {
	private String address;
	private int pincode;
	private String city;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
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
	private String state;
	@Override
	public String toString() {
		return "Address [address=" + address + ", pincode=" + pincode + ", city=" + city + ", state=" + state + "]";
	}
	
}
