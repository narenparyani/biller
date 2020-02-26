package com.billercrud.billerapi.model;

public class BillerType {
	private int billerTypeId;
	private String billerCategoryName;
	private String description;

	public BillerType() {

	}

	public BillerType(int billerTypeId, String billerCategoryName, String description) {
		this.billerTypeId = billerTypeId;
		this.billerCategoryName = billerCategoryName;
		this.description = description;
	}

	public int getBillerTypeId() {
		return billerTypeId;
	}

	public void setBillerTypeId(int billerTypeId) {
		this.billerTypeId = billerTypeId;
	}

	public String getBillerCategoryName() {
		return billerCategoryName;
	}

	public void setBillerCategoryName(String billerCatrgoryName) {
		this.billerCategoryName = billerCatrgoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "BillerType [billerTypeId=" + billerTypeId + ", billerCategoryName=" + billerCategoryName
				+ ", description=" + description + "]";
	}

}
