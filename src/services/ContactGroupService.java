package services;


import java.util.List;
import daos.ContactGroupDAO;
import entities.ContactGroup;

public class ContactGroupService {
	private ContactGroupDAO cgDAO;
	
	public ContactGroupService(ContactGroupDAO cgDAO) {
		this.cgDAO = cgDAO;
	}
	
	public ContactGroup createContactGroup(String groupName){
		return cgDAO.createContactGroup(groupName);
	}
	
	public void deleteContactGroupById(long id) {
		cgDAO.deleteContactGroup(id);
	}
	
	public boolean updateContactGroupById(long id, String groupName)  {
		return cgDAO.updateContactGroup(id,groupName);
	}
	
	public ContactGroup getContactGroupById(Long id) {
		return cgDAO.getContactGroupById(id);
	}
	
	public void deleteContactInGroup(long id, long idContact)  {
		cgDAO.deleteContactInGroup(id, idContact);
	}
	
	public boolean checkIfGroupExist(String nameGroup) {
		return cgDAO.checkIfGroupExist(nameGroup);
	}

	public ContactGroup getContactGroupByName(String contactGroups) {
		return cgDAO.getContactGroupByName(contactGroups);
	}
	
	public List<ContactGroup> listContactGroups() {
		return cgDAO.listContactGroup();
	}

	public boolean updateContactGroupByCG(ContactGroup contactGroup, String value) {
		return cgDAO.updateContactGroup(contactGroup, value);
	}
	
}


