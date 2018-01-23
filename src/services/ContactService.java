package services;

import java.util.ArrayList;
import java.util.List;
import daos.ContactDAO;
import entities.Contact;
import entities.Address;

public class ContactService {
	private ContactDAO cDAO;
	
	public ContactService(ContactDAO cDAO) {
		this.cDAO = cDAO;
	}
	
	public Contact createContact(String firstName, String lastName, String email, Address a) {
		return cDAO.createContact(firstName,lastName,email,a);
	}
	
	public void deleteContact(long idContact) {
		cDAO.deleteContact(idContact);
	}
	
	public List<Contact> listContacts() {
		return cDAO.listContact();
	}
	
	public Contact getContactById(long id){
		return cDAO.getContactById(id);
	}
	
	public void addContactInGroup(long id_cont, long id_group) {
		cDAO.addContactInGroup(id_cont, id_group);
	}
	
	public ArrayList<Contact> researchContacts(String recherche) {
		return cDAO.researchContacts(recherche);
	}

	public void updateContact(long idContact, String firstName, String lastName, String email) {
		cDAO.updateContact(idContact, firstName, lastName, email);
	}
}
