package com.billercrud.billerapi.model;

import java.util.*;

public class Phone {
	private Map<String, String> number;

	public Map<String, String> getNumber() {
		return number;
	}

	public void setNumber(Map<String, String> number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Phone [number=" + number + "]";
	}
}
