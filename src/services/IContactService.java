package services;

import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Contact;
import entities.IContact;

public interface IContactService {

	IContact createContact(String firstName, String lastName, String email, Address a);

	void deleteContact(long idContact);

	List<IContact> listContacts();

	IContact getContactById(long id);

	void addContactInGroup(long id_cont, long id_group);

	ArrayList<Contact> researchContacts(String recherche);

	void updateContact(long idContact, String firstName, String lastName, String email);

	boolean updateContact(IContact c, String firstName, String lastName, String email);

	ArrayList<Contact> researchContactsParam(String recherche);

}