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
	
	public static void deleteContact(String firstName, String lastName) {
		ContactDAO.deleteContact(firstName, lastName);
 	}
	
	public static void updateContact(Long idContact, String firstName, String lastName, String email, String siret, Long idAddr, String street, String zip, String city, String country, List<Long> idList, List<String> kindList, List<String> phoneList, String contactGroups) {
		ContactDAO.updateContact(idContact, firstName, lastName, email, siret, idAddr, street, zip, country, city, idList, kindList, phoneList, contactGroups);
	}
	
	public static List<Contact> listContacts() {
		return ContactDAO.listContact();
	}
	
	public static void addContactInGroup(long id_cont, long id_group) {
		ContactDAO.addContactInGroup(id_cont, id_group);
	}
	
	public static Contact researchContact(String firstName, String lastName) {
		return ContactDAO.researchContact(firstName, lastName);
	}
}
