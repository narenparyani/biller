package com.billercrud.billerapi.model;

public class PaymentMode {
	private int paymentModeId;
	private String paymentTypeName;

	public int getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	@Override
	public String toString() {
		return "PaymentMode [paymentModeId=" + paymentModeId + ", paymentTypeName=" + paymentTypeName + "]";
	}

}
