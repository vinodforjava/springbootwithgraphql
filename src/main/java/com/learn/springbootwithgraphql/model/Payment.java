package com.learn.springbootwithgraphql.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Payment {
	@Id
	private String paymentType;
	private String vendor;
	private Long amount;
	private String currency;

	public Payment(String transferType, String vendor, Long amount, String currency) {

		this.paymentType = transferType;
		this.vendor = vendor;
		this.amount = amount;
		this.currency = currency;
	}

	public Payment() {

	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
