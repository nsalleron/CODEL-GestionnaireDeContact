package daos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entities.Address;
import entities.Contact;
import entities.ContactGroup;
import entities.Entreprise;
import entities.IContact;
import entities.PhoneNumber;
import utils.HibernateUtil;

public  class EntrepriseDAO extends HibernateDaoSupport{

	
	public Entreprise createEntreprise(String firstName, String lastName, String email, Address a, String siret) {
		
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
	
	
	
	public void deleteEntreprise(String email) {
		//TODO
		
	}
	public void updateEntreprise(long idEntreprise, String firstName, String lastName, String email, String siret) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		long longSiret = Long.parseLong(siret);
		
		Entreprise contactEntre  = (Entreprise) session.load(Entreprise.class, idEntreprise);
		contactEntre.setNumSiret(longSiret);
		contactEntre.setFirstName(firstName);
		contactEntre.setLastName(lastName);
		contactEntre.setEmail(email);
		transaction.commit();
	}
	
	
	public void updateEntreprise(Long idEntreprise, String siret) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		long longSiret = Long.parseLong(siret);
		
		Entreprise contactEntre  = (Entreprise) session.load(Entreprise.class, idEntreprise);
		contactEntre.setNumSiret(longSiret);
		transaction.commit();
	}
	
	public List<Entreprise> listEntreprise(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Entreprise> lc = session.createCriteria(Entreprise.class).list();
		session.getTransaction().commit();
		
		return lc;
		
	}
	
	public void addEntrepriseInGroup(long id_cont, long id_group){
		
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



	public Entreprise getEntrepriseById(long id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		Entreprise entreprise = (Entreprise) session.get(Entreprise.class, id);
	
		transaction.commit();
		
		return entreprise;
	}



	public boolean updateEntreprise(IContact c, String firstName, String lastName, String email, String siret) {
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
	

	
}
