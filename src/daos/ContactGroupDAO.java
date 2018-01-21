package daos;


import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Contact;
import entities.ContactGroup;
import utils.HibernateUtil;

public class ContactGroupDAO {

	public static ContactGroup createContactGroup(String groupName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		ContactGroup contactGroup = new ContactGroup();
		contactGroup.setGroupName(groupName);

		Transaction transaction = session.getTransaction();
		if (!transaction.isActive())
			transaction = session.beginTransaction();
		session.persist(contactGroup);
		transaction.commit();

		return contactGroup;

	}

	public static boolean updateContactGroup(long id, String groupName) {
		// TryCatch versionning?
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction transation = session.getTransaction();
		if (!transation.isActive())
			transation = session.beginTransaction();
		ContactGroup contactGroup = (ContactGroup) session.load(ContactGroup.class, id);
		if (groupName != null && !groupName.isEmpty())
			contactGroup.setGroupName(groupName);
		transation.commit();
		return true;
	}

	public static  ContactGroup getContactGroupById(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if (!transaction.isActive())
			transaction = session.beginTransaction();
		ContactGroup contact = (ContactGroup) session.get(ContactGroup.class, id);
		return contact;
	}

	public  static boolean deleteContactGroup(long id) {
		//TryCatch versionning?
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if (!transaction.isActive())
			transaction = session.beginTransaction();

		ContactGroup contactGroup = (ContactGroup) session.load(ContactGroup.class, id);
		transaction = session.getTransaction();
		if (!transaction.isActive())
			transaction = session.beginTransaction();
		session.delete(contactGroup);
		transaction.commit();
		return true;

	}
	
	public static void deleteContactInGroup(long idGroup, long idContact) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if (!transaction.isActive())
			transaction = session.beginTransaction();

		ContactGroup contactGroup = (ContactGroup) session.load(ContactGroup.class, idGroup);
		Set<Contact> cg = contactGroup.getContacts();
		
		for(Contact c : cg) {
			if(c.getIdContact() == idContact)
				cg.remove(c);
		}
		contactGroup.setContacts(cg);
		
		transaction = session.getTransaction();
		if (!transaction.isActive())
			transaction = session.beginTransaction();
		session.saveOrUpdate(contactGroup);
		
		transaction.commit();
		
	}
	
	public static boolean checkIfGroupExist(String nameGroup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) transaction = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<ContactGroup> results = session.createQuery("select contactGroup from ContactGroup contactGroup").list();
		for (ContactGroup group : results) {
			if(group.getGroupName().equals(nameGroup)) {
				return true;
			}
		}
		transaction.commit();
		return false;
	}

	public static ContactGroup getContactGroupByName(String contactGroups) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) transaction = session.beginTransaction();
		
	
		return (ContactGroup) session.createQuery("select contactGroup from ContactGroup contactGroup "+
																"where contactGroup.groupName like '"+contactGroups+"'").uniqueResult();	
	}
	
	public static List<ContactGroup> listContactGroup(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ContactGroup> lc = session.createCriteria(ContactGroup.class).list();
		session.getTransaction().commit();
		
		return lc;
		
	}

}
