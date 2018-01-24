package daos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entities.Address;
import entities.Contact;
import entities.ContactGroup;
import entities.IContact;
import entities.PhoneNumber;

import utils.HibernateUtil;

public  class ContactDAO extends HibernateDaoSupport{
	
	public IContact createContact(String firstName, String lastName, String email, Address a) {
		//TODO Hibernate? alContact.add(new Contact(alContact.size(),firstName,lastName,email));
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
		IContact c = new Contact(firstName,lastName,email);
		c.setAdd(a);
		c.setPhones(new HashSet<PhoneNumber>());
		c.setBooks(new HashSet<ContactGroup>());
	
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();

		return c;
	}
	
	public void deleteContact(long idContact){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		IContact contact = (IContact) session.load(Contact.class, idContact);
			
			//On réouvre la session car delete deletePhoneNumber
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.getTransaction();
		if(!transaction.isActive())
			transaction = session.beginTransaction();
		session.delete(contact);
		
		transaction.commit();
			
	}
	
	public void updateContact(long idContact, String firstName, String lastName, String email) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		IContact contact  = (IContact) session.load(Contact.class, idContact);
		
		contact.setIdContact(idContact);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(email);
		
		transaction.commit();
		
	}
	
	public boolean updateContact(IContact c, String firstName, String lastName, String email) {
		// TODO Auto-generated method stub
		try {
			c.setFirstName(firstName);
			c.setLastName(lastName);
			c.setEmail(email);
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.getTransaction();
			if(!transaction.isActive()) 
				transaction = session.beginTransaction();
			
			//session.saveOrUpdate(c);	//throw ?
			session.merge(c); //throw?
			
			transaction.commit();
			return true;
		}catch(StaleObjectStateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	public static void deleteContact(String firstName, String lastName) {
		Contact contact = researchContact(firstName, lastName);
		
		for(PhoneNumber number : contact.getPhones()) {
			PhoneNumberService.deletePhoneNumberById(number);
		}
		
		AddressService.deleteAddress(contact.getAdd());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		session.delete(contact);
		
		transaction.commit();
}*/
	
	@SuppressWarnings("unchecked")
	public ArrayList<Contact> researchContacts(String recherche) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		ArrayList<Contact> listContact = new ArrayList<Contact>();
		
		if(recherche.length() > 0) {
			listContact.addAll(session.createCriteria(Contact.class)
					.add(Restrictions.like("firstName", "%"+recherche+"%")
							).list());
			listContact.addAll(session.createCriteria(Contact.class)
					.add(Restrictions.like("lastName", "%"+recherche+"%")
							).list());
			listContact.addAll(session.createCriteria(Contact.class)
					.add(Restrictions.like("email", "%"+recherche+"%")
							).list());
			Set<Contact> eliminateDoubleContact = new HashSet<Contact>();
			eliminateDoubleContact.addAll(listContact);
			listContact.clear();
			listContact.addAll(eliminateDoubleContact);
		
			transaction.commit();
		}
		return listContact;
	}
	
	public IContact getContactById(long id){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		IContact contact = (IContact) session.get(Contact.class, id);
	
		transaction.commit();
		
		return contact;
	}
	
	public List<IContact> listContact(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<IContact> lc = session.createCriteria(Contact.class).list();
		session.getTransaction().commit();
		
		return lc;
		
	}
	
	public void addContactInGroup(long id_cont, long id_group){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		Contact contact = (Contact) session.load(Contact.class, id_cont);
		ContactGroup cg = (ContactGroup) session.load(ContactGroup.class, id_group);

		Set<ContactGroup> ensGroupe = contact.getBooks();
		Set<Contact> ensContact = cg.getContacts();
		
		ensContact.add(contact);
		ensGroupe.add(cg);
		
		contact.setBooks(ensGroupe);
		cg.setContacts(ensContact);	
		
		transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		session.saveOrUpdate(contact);
		session.saveOrUpdate(cg);
		
		transaction.commit();
	}

	

	
	
}
