package services;

import java.util.ArrayList;
import java.util.List;
import daos.ContactDAO;
import entities.Contact;
import entities.IContact;
import entities.Address;

public class ContactService implements IContactService {
	private ContactDAO cDAO;
	
	public ContactService(ContactDAO cDAO) {
		this.cDAO = cDAO;
	}
	
	/* (non-Javadoc)
	 * @see services.IContactService#createContact(java.lang.String, java.lang.String, java.lang.String, entities.Address)
	 */
	@Override
	public IContact createContact(String firstName, String lastName, String email, Address a) {
		return cDAO.createContact(firstName,lastName,email,a);
	}
	
	/* (non-Javadoc)
	 * @see services.IContactService#deleteContact(long)
	 */
	@Override
	public void deleteContact(long idContact) {
		cDAO.deleteContact(idContact);
	}
	
	/* (non-Javadoc)
	 * @see services.IContactService#listContacts()
	 */
	@Override
	public List<IContact> listContacts() {
		return cDAO.listContact();
	}
	
	/* (non-Javadoc)
	 * @see services.IContactService#getContactById(long)
	 */
	@Override
	public IContact getContactById(long id){
		return cDAO.getContactById(id);
	}
	
	/* (non-Javadoc)
	 * @see services.IContactService#addContactInGroup(long, long)
	 */
	@Override
	public void addContactInGroup(long id_cont, long id_group) {
		cDAO.addContactInGroup(id_cont, id_group);
	}
	
	/* (non-Javadoc)
	 * @see services.IContactService#researchContacts(java.lang.String)
	 */
	@Override
	public ArrayList<Contact> researchContacts(String recherche) {
		return cDAO.researchContacts(recherche);
	}

	/* (non-Javadoc)
	 * @see services.IContactService#updateContact(long, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateContact(long idContact, String firstName, String lastName, String email) {
		cDAO.updateContact(idContact, firstName, lastName, email);
	}

	/* (non-Javadoc)
	 * @see services.IContactService#updateContact(entities.IContact, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateContact(IContact c, String firstName, String lastName, String email) {
		return cDAO.updateContact(c, firstName, lastName, email);
		
	}
}
