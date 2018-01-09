package services;

import java.util.List;

import daos.ContactDAO;
import entities.Contact;

public class ContactService {

	
	public static void CreateContact(String firstName, String lastName, String email) {
		ContactDAO.createContact(firstName,lastName,email);
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
}
