package daos;

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

public  class EntrepriseDAO {

	
	public static Entreprise createEntreprise(String firstName, String lastName, String email, Address a, String siret) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		long longSiret = 0;
		try {
			longSiret = Long.parseLong(siret);
		}catch(Exception e) {
			return null;
		}
		
		Entreprise entreprise = new Entreprise(firstName ,lastName,email, longSiret);
		entreprise.setAdd(a);
		entreprise.setPhones(new HashSet<PhoneNumber>());
		entreprise.setBooks(new HashSet<ContactGroup>());
		
		session.beginTransaction();
		session.save(entreprise);
		session.getTransaction().commit();

		return entreprise;
	}
	
	
	
	public static void deleteEntreprise(String email) {
		//TODO
		
	}
	
	public static void updateEntreprise(Long idEntreprise, String siret) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		long longSiret = Long.parseLong(siret);
		
		Entreprise contactEntre  = (Entreprise) session.load(Entreprise.class, idEntreprise);
		contactEntre.setNumSiret(longSiret);
		transaction.commit();
	}
	
	public static List<Entreprise> listEntreprise(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Entreprise> lc = session.createCriteria(Entreprise.class).list();
		session.getTransaction().commit();
		
		return lc;
		
	}
	
	public static void addEntrepriseInGroup(long id_cont, long id_group){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		Entreprise entreprise = (Entreprise) session.load(Entreprise.class, id_cont);
		ContactGroup cg = (ContactGroup) session.load(ContactGroup.class, id_group);
		
		
		Set<ContactGroup> ensGroupe = entreprise.getBooks();
		Set<Contact> ensContact = cg.getContacts();
		
		ensContact.add(entreprise);
		ensGroupe.add(cg);
		
		entreprise.setBooks(ensGroupe);
		cg.setContacts(ensContact);	
		
		transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		session.saveOrUpdate(entreprise);
		session.saveOrUpdate(cg);
		
		transaction.commit();
	}
	
}
