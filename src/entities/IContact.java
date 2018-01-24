package entities;

import java.util.Set;

public interface IContact {

	long getIdContact();

	void setIdContact(long idContact);

	String getFirstName();

	void setFirstName(String name);

	String getLastName();

	void setLastName(String lastName);

	String getEmail();

	void setEmail(String email);

	Address getAdd();

	void setAdd(Address add);

	Set<ContactGroup> getBooks();

	void setBooks(Set<ContactGroup> books);

	Set<PhoneNumber> getPhones();

	void setPhones(Set<PhoneNumber> phones);

	int getVersion();

	void setVersion(int version);

	String log();

}