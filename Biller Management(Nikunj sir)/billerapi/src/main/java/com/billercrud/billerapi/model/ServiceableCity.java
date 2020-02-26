package com.billercrud.billerapi.model;

import java.util.*;

public class ServiceableCity {
	private List<City> city;

	public ServiceableCity() {

	}

	public ServiceableCity(List<City> city) {
		super();
		this.city = city;
	}

	public List<City> getCity() {
		return city;
	}

	public void setCity(List<City> city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "ServiceableCity [city=" + city + "]";
	}

}
