package services;

import java.util.List;
import daos.ContactDAO;
import daos.EntrepriseDAO;
import entities.Address;
import entities.Entreprise;

public class EntrepriseService {

	
	public static Entreprise createEntreprise(String firstName, String lastName, String email, Address a, String siret) {
		return EntrepriseDAO.createEntreprise(firstName, lastName, email, a, siret);
	}
	
	public static void deleteEntreprise(String email) {
		EntrepriseDAO.deleteEntreprise(email);
	}
	
	public static void updateEntreprise(Long idEntreprise, String siret) {
		EntrepriseDAO.updateEntreprise(idEntreprise, siret);
	}
	
	public static List<Entreprise> listEntreprises() {
		return EntrepriseDAO.listEntreprise();
	}
	
	public static void addEntrepriseInGroup(long id_cont, long id_group) {
		ContactDAO.addContactInGroup(id_cont, id_group);
	}
}
