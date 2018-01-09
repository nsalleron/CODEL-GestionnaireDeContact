package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Contact;
import utils.HibernateUtil;

public  class ContactDAO {

	static ArrayList<Contact> alContact = new ArrayList<Contact>();
	
	public static void createContact(String firstName, String lastName, String email) {
		//TODO Hibernate? alContact.add(new Contact(alContact.size(),firstName,lastName,email));
		System.out.println("ADDED : "+firstName+" " + lastName +" at "+email);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Contact c = new Contact(firstName,lastName,email);
		
		session.beginTransaction();
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
