package com.billercrud.billerapi.model;

import java.util.*;

public class BillerPayMode {
	private List<PaymentMode> billerPaymentMode;

	

	public List<PaymentMode> getBillerPaymentMode() {
		return billerPaymentMode;
	}



	public void setBillerPaymentMode(List<PaymentMode> billerPaymentMode) {
		this.billerPaymentMode = billerPaymentMode;
	}



	@Override
	public String toString() {
		return "BillerPayMode [billerPaymentMode=" + billerPaymentMode + "]";
	}

	
}
