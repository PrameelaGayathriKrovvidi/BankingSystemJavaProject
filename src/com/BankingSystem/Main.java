package com.BankingSystem;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		BankService service = new BankService();
		
		while(true) {
			System.out.println("1. Create Account.");
			System.out.println("2. Login (accountNo + pin).");
			System.out.println("3. Deposit: ");
			System.out.println("4. Withdraw:");
			System.out.println("5. Check balance: ");
			System.out.println("6. Transfer money: ");
			System.out.println("7. Logout");
			System.out.println("8. Exit:");
			
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1: 
				System.out.println("Creating account: ");
				System.out.println("Enter name:");
				String name = sc.nextLine();
				System.out.println("Enter Pin: ");
				int pin = sc.nextInt();
				System.out.println("Enter initial Amount: ");
				double initialDeposit = sc.nextDouble();
				try {
					service.createAccount(name, pin, initialDeposit);
				} catch (InvalidPinException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			break;
			case 2:
				System.out.println("Logging to account..");
				System.out.println("Enter account no:");
				int accountNo = sc.nextInt();
				System.out.println("Enter pin: ");
				int loginPin = sc.nextInt();
				try {
					service.login(accountNo, loginPin);
				} catch (AccountNotFoundException | InvalidPinException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Depositing amount...");
				double depositAmount = sc.nextDouble();
				try {
					service.deposit(depositAmount);
				} catch (NotLoggedInException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 4:
				System.out.println("Withdrawal of amount...");
				double withdrawalAmount = sc.nextDouble();
				try {
					service.withdraw(withdrawalAmount);
				} catch (NotLoggedInException | InsufficientBalanceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5: 
				System.out.println("Checking balance: ");
				System.out.println("Loading....");
				try {
					service.checkBalance();
				} catch (NotLoggedInException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 6: 
				System.out.println("Tranfering money:");
				System.out.println("Enter Receiver Account Number:");
				int receiverAccountNo = sc.nextInt();
				System.out.println("Enter amount to transfer: ");
				double transferAmount = sc.nextDouble();
				try {
					service.transfer(receiverAccountNo, transferAmount);
				} catch (NotLoggedInException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InsufficientBalanceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 7:
				System.out.println("Logging out:");
				service.logout();
				break;
			case 8: System.out.println("Exit.");
				System.exit(0);
				break;
			default: System.out.println("Invalid choice");
			}
		}

	}

}
