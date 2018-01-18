package entities;

import java.util.Set;


public class Contact {
	private long idContact;
	private String firstName;
	private String lastName;
	private String email;
	private Address add;
	private Set<ContactGroup> books;
	private Set<PhoneNumber> phones;
	private int version;
	
	public Contact() {
		super();
	}
	
	public Contact(String name, String lastName, String email) {
		super();
		this.firstName = name;
		this.lastName = lastName;
		this.email = email;
	}

	public long getIdContact() {
		return idContact;
	}
	public void setIdContact(long idContact) {
		this.idContact = idContact;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String name) {
		this.firstName = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAdd() {
		return add;
	}

	public void setAdd(Address add) {
		this.add = add;
	}

	public Set<ContactGroup> getBooks() {
		return books;
	}

	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}

	public Set<PhoneNumber> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}

}
