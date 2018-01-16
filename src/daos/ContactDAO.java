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
	
	public static void createContact(String firstName, String lastName, String email, Address a) {
		//TODO Hibernate? alContact.add(new Contact(alContact.size(),firstName,lastName,email));
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Contact c = new Contact(firstName,lastName,email);
		c.setAdd(a);
		c.setPhones(new HashSet<PhoneNumber>());
		c.setBooks(new HashSet<ContactGroup>());
		
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		System.out.println("CONTACT ADDED");
	}
	public static void createContact(String firstName, String lastName, String email, Address a,String siret) {
		//TODO Hibernate? alContact.add(new Contact(alContact.size(),firstName,lastName,email));
		System.out.println("ADDED : "+firstName+" " + lastName +" at "+email);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Entreprise c = new Entreprise(firstName ,lastName,email, Long.parseLong(siret));
		HashSet<PhoneNumber> c2 = new HashSet<PhoneNumber>();
		c2.add(new PhoneNumber("+33","0125646546546",c));
		c.setPhones(c2);
		
		session.beginTransaction();
		session.save(c2);
		session.save(c);
		session.getTransaction().commit();
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
		return alContact;
	}
	
}
