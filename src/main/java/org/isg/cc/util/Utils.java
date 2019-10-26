package org.isg.cc.util;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.isg.cc.model.Organization;
import org.isg.cc.model.SearchableFactory;
import org.isg.cc.model.SearchableTypes;
import org.isg.cc.model.Ticket;
import org.isg.cc.model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

public class Utils {

	final static String USERS = "users.json";
	final static String TICKETS = "tickets.json";
	final static String ORGANIZATIONS = "organizations.json";

	static Object fileObj;
	static JSONArray fileArr;
	static JSONObject jsonObj;

	private static Utils instance = new Utils();

	private Utils() {
	}

	public static Utils getInstance() {
		return instance;
	}

	/**
	 * Read the givent file.
	 * 
	 * @param filename variable type of String. File name for search.
	 * @return Object type.
	 */
	public Object readJsonFile(String filename) throws Exception {

		FileReader reader = new FileReader(filename);
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}

	/**
	 * This search value for given key and file.
	 * 
	 * @param searchType is String type. And it is hold which file type to search.
	 * @param term       is String type. key for the search.
	 * @param value      is String type. value for match given key.
	 * @return SearchableTypes of object.
	 */
	public SearchableTypes search(String searchType, String term, String value) {
		String fileName = null;
		SearchableFactory factory = new SearchableFactory();
		SearchableTypes searchableObj = factory.getSearchableObj(searchType);

		try {

			if (searchType.equals("user")) {
				fileName = USERS;
			} else if (searchType.equals("org")) {
				fileName = ORGANIZATIONS;
			} else if (searchType.equals("ticket")) {
				fileName = TICKETS;
			}

			fileObj = readJsonFile(fileName);
			fileArr = (JSONArray) fileObj;

			Gson gson = new Gson();
			for (Object object : fileArr) {
				jsonObj = (JSONObject) object;

				if (term.equals("_id") || term.equals("organization_id") || term.equals("submitter_id")
						|| term.equals("assignee_id")) {

					if (searchType.equals("ticket") && term.equals("_id")) {
						if (jsonObj.get(term).equals(value)) {
							searchableObj = gson.fromJson(jsonObj.toJSONString(), searchableObj.getClass());
							return searchableObj;
						}
					} else {
						int intValue = ((Long) jsonObj.get(term)).intValue();
						if (intValue == Integer.parseInt(value)) {
							searchableObj = gson.fromJson(jsonObj.toJSONString(), searchableObj.getClass());
							return searchableObj;

						}
					}

//				} else if() {  // TODO: created_at, due_at, last_login_at check

				} else {
					if (jsonObj.get(term).equals(value)) {
						searchableObj = gson.fromJson(jsonObj.toJSONString(), searchableObj.getClass());
						return searchableObj;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * This search all the assigned and submitted tickets for given user.
	 * 
	 * @param User variable type of User
	 * @return User of object.
	 */
	public User setUserTicketsSubject(User user) {

		Map<String, String> subjectsOfTickets = new HashMap<String, String>();
		int userID = user.get_id();
		try {
			fileObj = readJsonFile(TICKETS);
			fileArr = (JSONArray) fileObj;
			int count = 1;
			for (Object object : fileArr) {
				jsonObj = (JSONObject) object;
				int submitID = ((Long) jsonObj.get("submitter_id")).intValue();
				int assignID = ((Long) jsonObj.get("assignee_id")).intValue();
				if (submitID == userID || assignID == userID) {
					subjectsOfTickets.put("ticket" + count, (String) jsonObj.get("subject"));
					count++;
				}
			}

			user.setSubjectsOfTickets(subjectsOfTickets);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	public Organization setOrgTicketsSubject(Organization organization) {

		Map<String, String> subjectsOfTickets = new HashMap<String, String>();
		int orgID = organization.get_id();
		try {
			fileObj = readJsonFile(TICKETS);
			fileArr = (JSONArray) fileObj;
			int count = 1;
			for (Object object : fileArr) {
				jsonObj = (JSONObject) object;
				if (jsonObj.get("organization_id") == null) {
					continue;
				}
				int ticketOrgID = ((Long) jsonObj.get("organization_id")).intValue();
				if (ticketOrgID == orgID) {
					subjectsOfTickets.put("ticket" + count, (String) jsonObj.get("subject"));
					count++;
				}
			}

			organization.setSubjectsOfTickets(subjectsOfTickets);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return organization;

	}

	public Organization setOrgUsers(Organization organization) {

		Map<String, String> orgUsers = new HashMap<String, String>();
		int orgID = organization.get_id();
		try {
			fileObj = readJsonFile(USERS);
			fileArr = (JSONArray) fileObj;
			int count = 1;
			for (Object object : fileArr) {
				jsonObj = (JSONObject) object;
				if (jsonObj.get("organization_id") == null) {
					continue;
				}
				int userOrgID = ((Long) jsonObj.get("organization_id")).intValue();
				if (userOrgID == orgID) {
					orgUsers.put("user" + count, (String) jsonObj.get("name"));
					count++;
				}
			}

			organization.setOrgUsers(orgUsers);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return organization;

	}

	public User setUserOrgName(User user) {

		try {
			fileObj = readJsonFile(ORGANIZATIONS);
			fileArr = (JSONArray) fileObj;
			int userOrgID = user.getOrganization_id();
			for (Object object : fileArr) {
				jsonObj = (JSONObject) object;
				int orgID = ((Long) jsonObj.get("_id")).intValue();
				if (orgID == userOrgID) {
					user.setOrganization_name(jsonObj.get("name").toString());
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public Ticket setTicketOrgName(Ticket ticket) {

		try {
			fileObj = readJsonFile(ORGANIZATIONS);
			fileArr = (JSONArray) fileObj;
			int ticketOrgID = ticket.getOrganization_id();
			for (Object object : fileArr) {
				jsonObj = (JSONObject) object;
				int orgID = ((Long) jsonObj.get("_id")).intValue();
				if (orgID == ticketOrgID) {
					ticket.setOrganization_name(jsonObj.get("name").toString());
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ticket;
	}

	public Ticket setTicketAssignee(Ticket ticket) {

		try {
			fileObj = readJsonFile(USERS);
			fileArr = (JSONArray) fileObj;
			int ticketAssigneeID = ticket.getAssignee_id();
			for (Object object : fileArr) {
				jsonObj = (JSONObject) object;
				int userID = ((Long) jsonObj.get("_id")).intValue();
				if (userID == ticketAssigneeID) {
					ticket.setAssignee_name(jsonObj.get("name").toString());
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ticket;
	}

	public Ticket setTicketSubmitter(Ticket ticket) {

		try {
			fileObj = readJsonFile(USERS);
			fileArr = (JSONArray) fileObj;
			int ticketSubmitterID = ticket.getSubmitter_id();
			for (Object object : fileArr) {
				jsonObj = (JSONObject) object;
				int userID = ((Long) jsonObj.get("_id")).intValue();
				if (userID == ticketSubmitterID) {
					ticket.setSubmitter_name(jsonObj.get("name").toString());
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ticket;
	}

	public void getKeys(String type) {
		String keyList = "";
		try {

			if (type.equals("user")) {
				fileObj = readJsonFile(USERS);
			} else if (type.equals("org")) {
				fileObj = readJsonFile(ORGANIZATIONS);
			} else if (type.equals("ticket")) {
				fileObj = readJsonFile(TICKETS);
			}

			fileArr = (JSONArray) fileObj;
			Set<String> keys = ((JSONObject) fileArr.get(0)).keySet();

			for (String key : keys) {
				keyList = keyList + key + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(keyList);
	}

}
