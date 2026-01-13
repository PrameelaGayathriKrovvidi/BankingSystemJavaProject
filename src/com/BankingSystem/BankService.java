package com.BankingSystem;

import java.util.HashMap;
import java.util.Map;

public class BankService {
	Map<Integer, Account> accounts = new HashMap<>();
	private int accountCounter = 1001;
	private Account loggedInAccount;
	
	
	public Account createAccount(String name, int pin, double initialDeposit) throws InvalidPinException {
		if(name == null || name.length() < 3) {
			throw new IllegalArgumentException("Name must have at least 3 characters");
		}
		
		if(pin < 1000 || pin > 9999) {
			throw new InvalidPinException("PIN must be 4 digits");
		}
		
		if(initialDeposit < 500) {
			throw new IllegalArgumentException("Minimum initial deposit is 500");
		}
		
		int accountNo = accountCounter++;
		Account account = new Account(accountNo, name, pin, initialDeposit);
		accounts.put(accountNo, account);
		return account;

	}
	
	public void login(int accountNo, int pin) throws AccountNotFoundException, InvalidPinException {
		if(!accounts.containsKey(accountNo)) {
			throw new AccountNotFoundException("Account does not exist. Please create an account.");
		}
		
		Account account = accounts.get(accountNo);
		
		if(account.getPin() != pin) {
			throw new InvalidPinException("Invalid PIN. Login failed.");
		}
		loggedInAccount = account;
		System.out.println("Login Successful. Welcome " + account.getName());
	}
	
	public void logout() {
		loggedInAccount = null;
		System.out.println("Logged out successfully.");
		
	}
	
	public boolean isLoggedIn() {
		return loggedInAccount != null;
	}
	
	
	public void deposit(double amount) throws NotLoggedInException {
		if(loggedInAccount == null) {
			throw new NotLoggedInException("Please login first to deposit money.");
		}
			if(amount <= 0) {
				throw new IllegalArgumentException("Deposit should be greater than 0");
			}
			
			double balance = loggedInAccount.getBalance();
			
			loggedInAccount.setBalance(balance + amount);
			System.out.println("Deposit successful.");
			System.out.println("Current balance  " + loggedInAccount.getBalance());
		
	}
	
	public void withdraw(double amount) throws NotLoggedInException,InsufficientBalanceException {
		if(loggedInAccount == null) {
			throw new NotLoggedInException("Please login first to withdraw money.");

		}
		
		if(amount <= 0) {
			System.out.println("Balance must be greater than 0");
			return;
		}
		
		double balance = loggedInAccount.getBalance();
		if(balance < amount) {
			throw new InsufficientBalanceException("Insufficient Funds.");
		}
		
		balance -= amount;
		loggedInAccount.setBalance(balance);
		System.out.println("Withdraw successful");
		System.out.println("Current balance  " + loggedInAccount.getBalance());
	}
	
	public void checkBalance() throws NotLoggedInException {
		if(loggedInAccount == null) {
			throw new NotLoggedInException("Please login to check balance.");
		}
		System.out.println("Total Balance: " + loggedInAccount.getBalance());
	}
	
	
	public void transfer(int receiverAccountNo, double amount) throws InsufficientBalanceException, NotLoggedInException {
		
		if(loggedInAccount == null) {
			throw new NotLoggedInException("Please login first to transfer money.");

		}
		
		if(amount <= 0) {
			System.out.println("Transfer amount must be greater than zero.");
			return;
		}
		
		if(loggedInAccount.getAccountNo() == receiverAccountNo) {
			System.out.println("Cannot transfer money to the same account.");
			return;
		};
		
		if(!accounts.containsKey(receiverAccountNo)) {
			System.out.println("Receiver Account does not exist.");
			return;
		}
		
		
		Account receiver = accounts.get(receiverAccountNo);
		
		if(loggedInAccount.getBalance() < amount) {
			throw new InsufficientBalanceException("Insufficient Funds.");

		}
		
		loggedInAccount.setBalance(loggedInAccount.getBalance() - amount);
		receiver.setBalance(receiver.getBalance() + amount);
		
		System.out.println("Transfer successful");
		System.out.println("Your balance: " + loggedInAccount.getBalance());
		
		
		
	}

}
