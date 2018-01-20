package services;

import java.util.ArrayList;
import java.util.List;
import daos.ContactDAO;
import entities.Contact;
import entities.Address;

public class ContactService {

	
	public static Contact createContact(String firstName, String lastName, String email, Address a) {
		return ContactDAO.createContact(firstName,lastName,email,a);
	}
	
	public static void deleteContact(long id) {
		ContactDAO.deleteContact(id);
	}
	
	public static List<Contact> listContacts() {
		return ContactDAO.listContact();
	}
	
	public static Contact getContactById(long id){
		return ContactDAO.getContactById(id);
	}
	
	public static void addContactInGroup(long id_cont, long id_group) {
		ContactDAO.addContactInGroup(id_cont, id_group);
	}
	
	public static ArrayList<Contact> researchContacts(String recherche) {
		return ContactDAO.researchContacts(recherche);
	}

	public static void updateContact(long idContact, String firstName, String lastName, String email) {
		ContactDAO.updateContact(idContact, firstName, lastName, email);
		
	}
}
