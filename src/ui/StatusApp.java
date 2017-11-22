package ui;

import java.util.ArrayList;

import business.Status;
import business.StatusDB;
import util.Console;

public class StatusApp {

	public static void main(String[] args) {
		System.out.println("Welcome to the Status app!");
		String choice = "";
		while (!choice.equalsIgnoreCase("exit")) {
			System.out.println("Options");
			System.out.println("all - Get all status");
			System.out.println("get - Get a single status by id");
			System.out.println("exit - exit app");
			choice = Console.getString("Option?:  ");
			if (choice.equalsIgnoreCase("all"))
				getAllStatus();
			else if (choice.equalsIgnoreCase("get"))
				getStatus();
		}
		System.out.println("Bye!");
	}
	
	private static void getStatus() {
		// get input from user
		int statusId = Console.getInt("Enter status id:  ");
		// do some logic - call the DB to get an instance of User
		StatusDB sdb = new StatusDB();
		Status s = sdb.getStatus(statusId);
		// print results to the screen
		System.out.println(s);
	}
	private static void getAllStatus() {
		StatusDB sdb = new StatusDB();
		ArrayList<Status> list = sdb.getAll();
		if (list!=null&&list.size()>0) {
			for (Status s: list) {
				System.out.println(s);
			}
		}
		else {
			System.out.println("No data returned.");
		}
	}

}
