package org.isg.cc.model;

import java.util.List;
import java.util.Map;

public class Organization implements SearchableTypes {

	private int _id;
	private String url;
	private String external_id;
	private String name;
	private List<String> domain_names;
	private String created_at;
	private String details;
	private boolean shared_tickets;
	private List<String> tags;
	private Map<String, String> subjectsOfTickets;
	private Map<String, String> orgUsers;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExternal_id() {
		return external_id;
	}

	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getDomain_names() {
		return domain_names;
	}

	public void setDomain_names(List<String> domain_names) {
		this.domain_names = domain_names;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isShared_tickets() {
		return shared_tickets;
	}

	public void setShared_tickets(boolean shared_tickets) {
		this.shared_tickets = shared_tickets;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public Map<String, String> getSubjectsOfTickets() {
		return subjectsOfTickets;
	}

	public void setSubjectsOfTickets(Map<String, String> subjectsOfTickets) {
		this.subjectsOfTickets = subjectsOfTickets;
	}
	
	public Map<String, String> getOrgUsers() {
		return orgUsers;
	}

	public void setOrgUsers(Map<String, String> orgUsers) {
		this.orgUsers = orgUsers;
	}
	
	public String getTicketsData() {
		String data = "";
		for (Map.Entry<String, String> entry : this.subjectsOfTickets.entrySet()) {
			data = data + "\n" + entry.getKey() + "\t\t\t" + entry.getValue().toString();
		}

		return data;
	}
	
	public String getUsersData() {
		String data = "";
		for (Map.Entry<String, String> entry : this.orgUsers.entrySet()) {
			data = data + "\n" + entry.getKey() + "\t\t\t" + entry.getValue().toString();
		}

		return data;
	}

	@Override
	public String toString() {
		return "external_id\t\t" + this.external_id + "\nname\t\t\t" + this.name + "\ndomain_names\t\t"
				+ this.domain_names + "\ncreated_at\t\t" + this.created_at + "\ndetails\t\t\t" + this.details
				+ "\nshared_tickets\t\t" + this.shared_tickets + "\ntags\t\t\t"
				+ this.tags  + getUsersData() + getTicketsData();
	}
}