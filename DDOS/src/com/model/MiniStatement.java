package com.model;

/**
 * This class is used for Ministatement. 
 * @author Renuka Gore
 *
 */

public class MiniStatement {
	
	private int transId;
	private double transAmount;
	private double userBalance;
	private String transTime;
	private String transType;
	
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public double getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}
	public double getUserBalance() {
		return userBalance;
	}
	public void setUserBalance(double userBalance) {
		this.userBalance = userBalance;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	@Override
	public String toString() {
		return "MiniStatement [transId=" + transId + ", transAmount="
				+ transAmount + ", userBalance=" + userBalance + ", transTime="
				+ transTime + "]";
	}
	
	
	

}
