package services;

import java.util.List;
import daos.ContactDAO;
import entities.Contact;
import entities.Address;

public class ContactService {

	
	public static Contact createContact(String firstName, String lastName, String email, Address a) {
		return ContactDAO.createContact(firstName,lastName,email,a);
	}
	
	public static void deleteContact(String email) {
		ContactDAO.deleteContact(email);
	}
	
	public static void updateContact(String firstName, String lastName, String email) {
		ContactDAO.updateContact(firstName, lastName, email);
	}
	
	public static List<Contact> listContact() {
		return ContactDAO.listContact();
	}
	
	public static void addContactInGroup(long id_cont, long id_group) {
		ContactDAO.addContactInGroup(id_cont, id_group);
	}
}
