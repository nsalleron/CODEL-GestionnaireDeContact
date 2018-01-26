package daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import entities.Address;
import entities.Contact;
import entities.IContact;

public interface IContactDAO {

	IContact createContact(String firstName, String lastName, String email, Address a);

	void deleteContact(long idContact);

	void updateContact(long idContact, String firstName, String lastName, String email);

	boolean updateContact(IContact c, String firstName, String lastName, String email);

	ArrayList<Contact> researchContacts(String recherche);

	ArrayList<Contact> researchContactsParam(String recherche);
	
	ArrayList<Contact> researchContactsSimple(String recherche);

	IContact getContactById(long id);

	List<IContact> listContact();

	void addContactInGroup(long id_cont, long id_group);

}