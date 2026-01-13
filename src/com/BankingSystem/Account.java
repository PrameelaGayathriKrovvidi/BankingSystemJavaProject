package com.BankingSystem;

public class Account {
	private int accountNo;
	private String name;
	private int pin;
	private double balance;

	
	public Account(int accountNo, String name, int pin, double balance) {
		this.accountNo = accountNo;
		this.name = name;
		this.pin = pin;
		this.balance = balance;
	}
	
	public int getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPin() {
		return pin;
	}
	
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", name=" + name + ", balance=" + balance + "]";
	}
	
	
}
