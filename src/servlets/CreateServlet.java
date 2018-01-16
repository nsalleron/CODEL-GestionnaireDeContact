package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.AddressDAO;
import daos.PhoneNumberDAO;
import entities.Address;
import entities.Contact;
import entities.ContactGroup;
import entities.PhoneNumber;
import services.AddressService;
import services.ContactGroupService;
import services.ContactService;
import services.PhoneNumberService;

/**
 * Servlet implementation class CreateServlet
 */
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Déclaration */
		String firstName, lastName, email, siret, street,
			   city, zip, country, phone, phonekind, contactGroups;
		boolean okFirstName = false, okLastName = false ,
				okEmail= false , okStreet = false ,
				okZip = false, okCity = false, okCountry = false, okPhoneKind = false, okPhone = false;
		
		/* Récupération des élements */
		firstName = request.getParameter("firstname");
		lastName = request.getParameter("lastname");
		email = request.getParameter("email");
		siret = request.getParameter("siret");
		street = request.getParameter("city");
		city = request.getParameter("city");
		zip = request.getParameter("zip");
		country = request.getParameter("country");
		phone = request.getParameter("telephone");
		phonekind = request.getParameter("phonekind");
		contactGroups = request.getParameter("contact_groups");
		
		/* Vérification des élements */
		if(firstName != null && firstName.length() > 0) 
			okFirstName = true;
		if(lastName!=null && lastName.length()>0)
			okLastName = true;
		if(email!=null && email.length()>5 && email.contains("@") && email.contains("."))
			okEmail = true;
		if(street!=null && street.length()>0) 
			okStreet = true;
		if(zip!=null && zip.length()>0) 
			okZip = true;
		if(city!=null && city.length()>0) 
			okCity = true;
		if(country!=null && country.length()>0) 
			okCountry = true;
		if(phone!=null && phone.length()>0) 
			okPhone = true;
		if(phonekind!=null && phonekind.length()>0) 
			okPhoneKind = true;
		
		/* Création */
		if(okFirstName && okLastName && okEmail && okStreet && okZip && okCity && okCountry){
			
			Address add = AddressService.createAddress(street, city, zip, country);
			Contact c = null;
			ContactGroup cg = null;
			
			if(siret!=null && !siret.isEmpty()){
				c = ContactService.CreateContact(firstName, lastName, email, add, siret);
			} else {
				c = ContactService.CreateContact(firstName, lastName, email, add);
			}
			
			PhoneNumberService.createPhoneNumber(phonekind, phone, c);
			if(contactGroups != null) {
				if(!ContactGroupService.checkIfGroupExist(contactGroups))
					cg = ContactGroupService.createContactGroup(contactGroups);
				else
					cg = ContactGroupService.getContactGroupByName(contactGroups);
			}else {
				if(!ContactGroupService.checkIfGroupExist("_PAS_DE_GROUPE_"))
					cg = ContactGroupService.createContactGroup("_PAS_DE_GROUPE_");
				else
					cg = ContactGroupService.getContactGroupByName("_PAS_DE_GROUPE_");
			}
			ContactService.addContactInGroup(c.getIdContact(), cg.getIdContactGroup());
			
			
			request.setAttribute("success", true);
			request.setAttribute("information", "Contact created !");
			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			rd.forward(request, response);
		} else {
			
			String who = whoFails(okFirstName, okLastName, okEmail, okStreet, okZip, okCity, okCountry, okPhoneKind, okPhone);
			request.setAttribute("success", false);
			request.setAttribute("information",who);
			RequestDispatcher rd = request.getRequestDispatcher("CreateContact.jsp");
			rd.forward(request, response);
		}	
	}
	private String whoFails(boolean first, boolean last, boolean email, boolean street, boolean zip, boolean city, boolean country, boolean phonekind, boolean phone) {
		String tmp = "";
		if(!first)
			tmp += "firstname;";
		if(!last)
			tmp += "lastname;";
		if(!email)
			tmp += "email;";
		if(!street)
			tmp += "street;";
		if(!zip)
			tmp += "zip;";
		if(!city)
			tmp += "city;";
		if(!country)
			tmp += "country;";
		if(!phonekind)
			tmp += "phonekind;";
		if(!phone)
			tmp += "phone";
		return tmp;
	}

}
