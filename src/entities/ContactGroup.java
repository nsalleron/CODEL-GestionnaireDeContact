package entities;

import java.util.Set;

public class ContactGroup {
	private long idContactGroup;
	private String groupName;
	private Set<Contact> contacts;
	
	public ContactGroup() {
		super();
	}
	
	
	public ContactGroup(long id_group, String groupName,Set<Contact> contacts) {
		super();
		this.idContactGroup = id_group;
		this.groupName = groupName;
		this.contacts = contacts;
	}
	
	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public ContactGroup(String groupName) {
		super();
		this.groupName = groupName;
	}
	public long getIdContactGroup() {
		return idContactGroup;
	}
	public void setIdContactGroup(long idGroup) {
		this.idContactGroup = idGroup;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
