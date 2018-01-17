package services;

import java.util.List;
import daos.ContactDAO;
import entities.Contact;
import utils.HibernateUtil;
import entities.Address;

public class ContactService {

	
	public static Contact CreateContact(String firstName, String lastName, String email, Address a) {
		return ContactDAO.createContact(firstName,lastName,email,a);
	}
	public static Contact CreateContact(String firstName, String lastName, String email, Address a, String Siret) {
		return ContactDAO.createContact(firstName,lastName,email,a, Siret);
	}
	
	public static void DeleteContact(String email) {
		ContactDAO.deleteContact(email);
	}
	
	public static void UpdateContact(String firstName, String lastName, String email) {
		ContactDAO.updateContact(firstName, lastName, email);
	}
	
	public static List<Contact> ReadContact() {
		return ContactDAO.readContact();
	}
	
	public static void addContactInGroup(long id_cont, long id_group) {
		ContactDAO.addContactInGroup(id_cont, id_group);
	}
}
