package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Address;
import entities.Contact;
import entities.Entreprise;
import services.AddressService;
import services.ContactGroupService;
import services.ContactService;

/**
 * Servlet implementation class CreateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName, lastName, email, siret, street,
		city, zip, country, phoneMaison, phonePortable, 
		phoneBureau, phonekind, contactGroups, kindBureau, kindMaison, kindPortable,
		idBureau, idMaison, idPortable, idContactS, idAddrS, versionContact = null,versionAddress = null;

		/* Récupération des élements */
		zip = request.getParameter("zip");
		city = request.getParameter("city");
		email = request.getParameter("email");
		siret = request.getParameter("siret");
		street = request.getParameter("street");
		country = request.getParameter("country");
		lastName = request.getParameter("lastname");
		firstName = request.getParameter("firstname");
		versionContact = request.getParameter("version");
		System.out.println("UpdateServlet : VERSION : "+versionContact+"SIRET : "+siret);

		phoneMaison = request.getParameter("telephone1");
		phoneBureau = request.getParameter("telephone3");
		phonePortable = request.getParameter("telephone2");

		kindMaison = request.getParameter("kind1");
		kindBureau = request.getParameter("kind3");
		kindPortable = request.getParameter("kind2");

		idMaison = request.getParameter("iD1");
		idBureau = request.getParameter("iD3");
		idPortable = request.getParameter("iD2");

		idContactS = request.getParameter("IdContact");
		idAddrS = request.getParameter("IdAddr");
		contactGroups = request.getParameter("contact_groups");

		Long idAddr = Long.parseLong(idAddrS);
		Long idContact = Long.parseLong(idContactS);

		List<Long> idList = new ArrayList<>();
		List<String> kindList = new ArrayList<>();
		List<String> phoneList = new ArrayList<>();

		if(idMaison != null && !idMaison.isEmpty()) {
			idList.add(Long.parseLong(idMaison));
			phoneList.add(phoneMaison);
			kindList.add(kindMaison);
		}

		if(idBureau != null && !idBureau.isEmpty()) {
			idList.add(Long.parseLong(idBureau));
			phoneList.add(phoneBureau);
			kindList.add(kindBureau);
		}

		if(idPortable != null && !idPortable.isEmpty()) {
			idList.add(Long.parseLong(idPortable));
			phoneList.add(phonePortable);
			kindList.add(kindPortable);
		}	  
		
		if(siret != null && siret.length() > 0) {
			//TODO
		}else {
			Contact c = ContactService.getContactById(idContact);
			System.out.println("UpdateServelt : VERSION ----> "+c.getVersion()+"MA VERSION : "+Integer.parseInt(versionContact));
			if(c.getVersion() != Integer.parseInt(versionContact) ){//|| 
				//	AddressService.getAddress(idAddr).getVersion() > Integer.parseInt(versionAddress) ) {
				//TODO Vérification sur les contactGroups de ce contact + sur les PhoneNumber de ce contact
				String rep = "La mise à jour à échouer. "
						+ "Il y a eu une update en concurrence avec la votre sur le contact : "+c.getLastName() + " " + c.getFirstName();
				request.setAttribute("updatefailed",rep); 
				System.out.println("FAILED : "+rep);
				RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
				
				rd.forward(request, response);
			}else {//TODO
				c.setFirstName(firstName);
				c.setLastName(lastName);
				c.setEmail(email);
				
				Address a = AddressService.getAddress(idAddr);
				a.setStreet(street);
				a.setCity(city);
				a.setCountry(country);
				a.setZip(zip);
				
										//		x		x			x		x				x		x	x		x		x		x		x		x			x
				ContactService.updateContact(idContact, firstName, lastName, email, siret, idAddr, street, zip, country, city, idList, kindList, phoneList, contactGroups);

				RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
				rd.forward(request, response);
			}
			
			
		}
		
	}

}
