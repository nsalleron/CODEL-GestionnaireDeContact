package services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import daos.ContactGroupDAO;
import daos.ContactDAO;
import entities.Contact;
import entities.ContactGroup;
import utils.HibernateUtil;

public class ContactGroupService {

	
	public static ContactGroup createContactGroup(String groupName){
		return ContactGroupDAO.createContactGroup(groupName);
	}
	
	public static void deleteContactGroupById(long id) {
		ContactGroupDAO.deleteContactGroup(id);
	}
	
	public static boolean updateContactGroupById(long id, String groupName)  {
		return ContactGroupDAO.updateContactGroup(id,groupName);
	}
	
	public static ContactGroup getContactGroupById(Long id) {
		return ContactGroupDAO.getContactGroupById(id);
	}
	
	public static void deleteContactInGroup(long id, long idContact)  {
		ContactGroupDAO.deleteContactInGroup(id, idContact);
	}
	
	public static boolean checkIfGroupExist(String nameGroup) {
		return ContactGroupDAO.checkIfGroupExist(nameGroup);
	}

	public static ContactGroup getContactGroupByName(String contactGroups) {
		return ContactGroupDAO.getContactGroupByName(contactGroups);
	}
	
}


