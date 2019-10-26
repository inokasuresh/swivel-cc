package org.isg.cc.controller;

import java.util.Scanner;

import org.isg.cc.model.Organization;
import org.isg.cc.model.Ticket;
import org.isg.cc.model.User;
import org.isg.cc.util.Utils;


public class App {

	final static String USERS = "user";
	final static String TICKETS = "ticket";
	final static String ORGANIZATIONS = "org";

	static String CHOICE;
	static String TERM;
	static String VALUE;

	public static void main(String... args) {

		Scanner scanner = new Scanner(System.in);
		Utils utils = Utils.getInstance();

		System.out.println("Type 'quit' to exit any time, press 'Enter' to continue.");
		System.out.println();
		System.out.println("Select search option");
		System.out.println("\t Press 1 to search");
		System.out.println("\t Press 2 to view a list of searchable fields");
		System.out.println("\t Type 'quit' to exit");

		CHOICE = scanner.next();
		try {

			if (CHOICE.equalsIgnoreCase("quit")) {
				System.exit(0);

			} else if (CHOICE.equalsIgnoreCase("1")) {
				System.out.println("Select 1) User or 2) Ticket or 3) Organizations");
				CHOICE = scanner.next();
				if (CHOICE.equalsIgnoreCase("quit")) {
					System.exit(0);
				}

				System.out.println("Enter search term");
				TERM = scanner.next();
				if (TERM.equalsIgnoreCase("quit")) {
					System.exit(0);
				}

				System.out.println("Enter search value");
				VALUE = scanner.next();
				if (VALUE.equalsIgnoreCase("quit")) {
					System.exit(0);
				}

				if (CHOICE.equalsIgnoreCase("1")) {

					User user = (User) utils.search(USERS, TERM, VALUE);
					if (user == null) {
						System.out.println("No data found.");
					} else {
						user = utils.setUserTicketsSubject(user);
						user = utils.setUserOrgName(user);
						System.out.println(user.toString());
					}

				} else if (CHOICE.equalsIgnoreCase("2")) {

					Ticket ticket = (Ticket) utils.search(TICKETS, TERM, VALUE);
					if (ticket == null) {
						System.out.println("No data found.");
					} else {
						ticket = utils.setTicketAssignee(ticket);
						ticket = utils.setTicketSubmitter(ticket);
						ticket = utils.setTicketOrgName(ticket);
						System.out.println(ticket.toString());
					}

				} else if (CHOICE.equalsIgnoreCase("3")) {

					Organization organization = (Organization) utils.search(ORGANIZATIONS, TERM, VALUE);
					if (organization == null) {
						System.out.println("No data found.");
					} else {
						organization = utils.setOrgTicketsSubject(organization);
						organization = utils.setOrgUsers(organization);
						System.out.println(organization.toString());
					}

				}

			} else if (CHOICE.equalsIgnoreCase("2")) {
				System.out.println("Search Users with");
				utils.getKeys(USERS);
				System.out.println("------------------");
				System.out.println("Search Ticket with");
				utils.getKeys(TICKETS);
				System.out.println("------------------");
				System.out.println("Search Users with");
				utils.getKeys(ORGANIZATIONS);

			} else {
				System.out.println("Plese enter valide selection.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
