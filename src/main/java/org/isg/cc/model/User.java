package org.isg.cc.model;

import java.util.List;
import java.util.Map;

public class User implements SearchableTypes {

	private int _id;
	private String url;
	private String external_id;
	private String name;
	private String alias;
	private String created_at;
	private boolean active;
	private boolean verified;
	private boolean shared;
	private String locale;
	private String timezone;
	private String last_login_at;
	private String email;
	private String phone;
	private String signature;
	private int organization_id;
	private List<String> tags;
	private boolean suspended;
	private String role;
	private Map<String, String> subjectsOfTickets;
	private String organization_name;

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getLast_login_at() {
		return last_login_at;
	}

	public void setLast_login_at(String last_login_at) {
		this.last_login_at = last_login_at;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(int organization_id) {
		this.organization_id = organization_id;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Map<String, String> getSubjectsOfTickets() {
		return subjectsOfTickets;
	}

	public void setSubjectsOfTickets(Map<String, String> subjectsOfTickets) {
		this.subjectsOfTickets = subjectsOfTickets;
	}

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	public String getTicketsData() {
		String data = "";
		for (Map.Entry<String, String> entry : this.subjectsOfTickets.entrySet()) {
			data = data + "\n" + entry.getKey() + "\t\t\t" + entry.getValue().toString();
		}

		return data;
	}

	@Override
	public String toString() {
		return "external_id\t\t" + this.external_id + "\nname\t\t\t" + this.name + "\nalias\t\t\t" + this.alias
				+ "\ncreated_at\t\t" + this.created_at + "\nactive\t\t\t" + this.active + "\nverified\t\t"
				+ this.verified + "\nshared\t\t\t" + this.shared + "\nlocale\t\t\t" + this.locale + "\ntimezone\t\t"
				+ this.timezone + "\nlast_login_at\t\t" + this.last_login_at + "\nemail\t\t\t" + this.email
				+ "\nphone\t\t\t" + this.phone + "\nsignature\t\t" + this.signature + "\norganization_id\t\t"
				+ this.organization_id + "\ntags\t\t\t" + this.tags + "\nsuspended\t\t" + this.suspended
				+ "\nrole\t\t\t" + this.role + "\norganization_name\t" + this.organization_name + getTicketsData();

	}
}
