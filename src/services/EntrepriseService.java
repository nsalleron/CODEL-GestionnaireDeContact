package services;

import java.util.List;
import daos.ContactDAO;
import daos.EntrepriseDAO;
import entities.Address;
import entities.Entreprise;

public class EntrepriseService {
	private EntrepriseDAO eDAO;
	private ContactDAO cDAO;
	
	public EntrepriseService(EntrepriseDAO eDAO, ContactDAO cDAO) {
		this.eDAO = eDAO;
		this.cDAO = cDAO;
	}
	
	public Entreprise createEntreprise(String firstName, String lastName, String email, Address a, String siret) {
		return eDAO.createEntreprise(firstName, lastName, email, a, siret);
	}
	
	public void deleteEntreprise(String email) {
		eDAO.deleteEntreprise(email);
	}
	
	public void updateEntreprise(Long idEntreprise, String siret) {
		eDAO.updateEntreprise(idEntreprise, siret);
	}
	
	public List<Entreprise> listEntreprises() {
		return eDAO.listEntreprise();
	}
	
	public void addEntrepriseInGroup(long id_cont, long id_group) {
		cDAO.addContactInGroup(id_cont, id_group);
	}
	
	public Entreprise getEntrepriseById(long id) {
		return eDAO.getEntrepriseById(id);
	}

	public void updateEntreprise(long idEntreprise, String firstName, String lastName, String email, String siret) {
		eDAO.updateEntreprise(idEntreprise, firstName, lastName, email, siret);		
	}

}
