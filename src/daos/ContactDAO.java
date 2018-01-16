package daos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
	
}
