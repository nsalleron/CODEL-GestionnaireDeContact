package daos;


import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entities.Contact;
import entities.ContactGroup;
import entities.IContact;
import utils.HibernateUtil;

public class ContactGroupDAO extends HibernateDaoSupport{

	public ContactGroup createContactGroup(String groupName) {
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

	public boolean updateContactGroup(long id, String groupName) {
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

	public  ContactGroup getContactGroupById(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if (!transaction.isActive())
			transaction = session.beginTransaction();
		ContactGroup contact = (ContactGroup) session.get(ContactGroup.class, id);
		return contact;
	}

	public boolean deleteContactGroup(long id) {
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

	public void deleteContactInGroup(long idGroup, long idContact) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if (!transaction.isActive())
			transaction = session.beginTransaction();

		ContactGroup contactGroup = (ContactGroup) session.load(ContactGroup.class, idGroup);
		Set<Contact> cg = contactGroup.getContacts();

		for(IContact c : cg) {
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

	public boolean checkIfGroupExist(String nameGroup) {
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

	public ContactGroup getContactGroupByName(String contactGroups) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) transaction = session.beginTransaction();


		return (ContactGroup) session.createQuery("select contactGroup from ContactGroup contactGroup "+
				"where contactGroup.groupName like '"+contactGroups+"'").uniqueResult();	
	}

	public List<ContactGroup> listContactGroup(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ContactGroup> lc = session.createCriteria(ContactGroup.class).list();
		session.getTransaction().commit();

		return lc;

	}

	public boolean updateContactGroup(ContactGroup contactGroup, String value) {

		try {
			contactGroup.setGroupName(value);

			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.getTransaction();
			if(!transaction.isActive()) 
				transaction = session.beginTransaction();

			session.merge(contactGroup); 

			transaction.commit();
			return true;
		}catch(StaleObjectStateException e) {
			e.printStackTrace();
			return false;
		}

	}

}
