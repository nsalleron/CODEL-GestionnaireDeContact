package daos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Address;
import entities.Contact;
import entities.ContactGroup;
import entities.Entreprise;
import entities.PhoneNumber;
import utils.HibernateUtil;

public  class ContactDAO {

	static ArrayList<Contact> alContact = new ArrayList<Contact>();
	
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
	public static Contact createContact(String firstName, String lastName, String email, Address a, String siret) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("DEBUG OBJT SESSION : "+session.toString());
		long longSiret = Long.parseLong(siret);
		System.out.println("Siret : "+siret);
		System.out.println("CastSiret : "+longSiret);
		Contact c = new Entreprise(firstName ,lastName,email, longSiret);
		c.setAdd(a);
		c.setPhones(new HashSet<PhoneNumber>());
		c.setBooks(new HashSet<ContactGroup>());
		
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		
		System.out.println("ENTREPRISE ADDED");
		return c;
	}
	
	
	
	public static void deleteContact(String email) {
		for (Contact contact : alContact) {
			if (contact.getEmail().equals(email)){
				alContact.remove((int) contact.getIdContact());
				System.out.println("REMOVE : "+contact.getFirstName()+" " + contact.getLastName() +" at "+contact.getEmail());
				break;
			}
		}
		
	}
	
	public static void updateContact(String firstName, String lastName, String email) {
		for (Contact contact : alContact) {
			if (contact.getFirstName().equals(firstName) &&
					contact.getLastName().equals(lastName) ){
				contact.setEmail(email);
				System.out.println("UPDATED : "+contact.getFirstName()+" " + contact.getLastName() +" at "+contact.getEmail());
				break;
			}
		}
		
		
	}
	
	public static List<Contact> readContact(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("DEBUG OBJT SESSION : "+session.toString());
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
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
