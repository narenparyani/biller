package com.billercrud.billerapi.model;

import java.util.*;

public class Email {
	private Map<String, String> emaiId;

	public Map<String, String> getEmaiId() {
		return emaiId;
	}

	public void setEmaiId(Map<String, String> emaiId) {
		this.emaiId = emaiId;
	}

	@Override
	public String toString() {
		return "Email [emaiId=" + emaiId + "]";
	}

}
