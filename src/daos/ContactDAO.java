package daos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entities.Address;
import entities.Contact;
import entities.ContactGroup;
import entities.PhoneNumber;
import services.AddressService;
import services.PhoneNumberService;
import utils.HibernateUtil;

public  class ContactDAO {
	
	public static Contact createContact(String firstName, String lastName, String email, Address a) {
		//TODO Hibernate? alContact.add(new Contact(alContact.size(),firstName,lastName,email));
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("DEBUG OBJT SESSION : "+session.toString());
		Contact c = new Contact(firstName,lastName,email);
		c.setAdd(a);
		c.setPhones(new HashSet<PhoneNumber>());
		c.setBooks(new HashSet<ContactGroup>());
		
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();

		
		System.out.println("CONTACT ADDED");
		return c;
	}
	
	public static void deleteContact(String email) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		Long id = Long.parseLong("2");
		Contact contact = (Contact) session.load(Contact.class, id);
		
		//On réouvre la session car delete deletePhoneNumber
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(contact);
		tx.commit();
	}
	
	public static void updateContact(Long idContact, String firstName, String lastName, String email, String siret, Long idAddr, String street, String zip, String country, String city,  List<Long> idList, List<String> kindList, List<String> phoneList, String contactGroups) {
		AddressService.updateAddress(idAddr, street, city, zip, country);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		Contact contact  = (Contact) session.load(Contact.class, idContact);
		Address addr = new Address(street, city, zip, country);
		addr.setIdAddress(idAddr);
		
		Set<PhoneNumber> number = new HashSet<PhoneNumber>();
		
		for(int i=0; i<phoneList.size(); i++) {
			number.add(new PhoneNumber(kindList.get(i), phoneList.get(i), contact));
		}
		
		contact.setIdContact(idContact);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setPhones(number);
		contact.setEmail(email);
		contact.setAdd(addr);
		
		transaction.commit();
		
		PhoneNumberService.updatePhoneNumberById(idList, kindList, phoneList, contact);
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
	public static ArrayList<Contact> researchContacts(String recherche) {
		
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
	
	public static Contact getContactById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		Contact contact = (Contact) session.load(Contact.class, id);
		return contact;
	}
	
	public static List<Contact> listContact(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Contact> lc = session.createCriteria(Contact.class).list();
		session.getTransaction().commit();
		
		return lc;
		
	}
	
	public static void addContactInGroup(long id_cont, long id_group){
		
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
