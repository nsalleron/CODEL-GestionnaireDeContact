package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import entities.Contact;
import entities.IContact;
import services.AddressService;
import services.ContactGroupService;
import services.EntrepriseService;
import services.IContactService;
import services.PhoneNumberService;

/**
 * Servlet implementation class RechercheServlet
 */
@WebServlet("/RechercheServlet")
public class RechercheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheServlet() {
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
		String idContact = request.getParameter("idcontact");
		String recherche = request.getParameter("recherche");
		String modeDelete = request.getParameter("delete");
		
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		IContactService contactService = (IContactService) context.getBean("beanContactService");
		EntrepriseService entrepriseService =  (EntrepriseService) context.getBean("beanEntrepriseService");
		if(idContact == null && modeDelete == null) {	/* En mode update */
			ArrayList<Contact> contacts = contactService.researchContacts(recherche);
			RequestDispatcher rd = request.getRequestDispatcher("UpdateContact.jsp");
			request.setAttribute("contacts", contacts);
			request.setAttribute("recherche", recherche);
			rd.forward(request, response);
		}else if (idContact == null && modeDelete != null){	/* En mode delete */
			ArrayList<Contact> contacts;
			
			/* Le code suivant sert juste à montrer que les deux requêtes retourne des résultats identiques */
			ArrayList<Contact> rechercheAvecParam = contactService.researchContactsParam(recherche);	//Avec Param
			ArrayList<Contact> rechercheSimple = contactService.researchContactsSimple(recherche); // Simple
			ArrayList<Contact> listResult = new ArrayList<Contact>();
			
		
			for(Contact c : rechercheAvecParam) 
				for(Contact cs : rechercheSimple) 
					if(c.getIdContact() == cs.getIdContact())
						listResult.add(c);		
			
			if(listResult.size() != rechercheAvecParam.size() &&
					listResult.size() != rechercheSimple.size()) {
				throw new ArrayStoreException();
			}else {
				System.out.println("La recherche simple et la recherche par paramètre ont retournées les mêmes résultats !");
				contacts = rechercheSimple;
			}
			/*Fin de vérification */
			RequestDispatcher rd = request.getRequestDispatcher("DeleteContact.jsp");
			request.setAttribute("contacts", contacts);
			request.setAttribute("recherche", recherche);
			rd.forward(request, response);
		}else if(modeDelete == null && idContact.length() > 0){	/* mode update */
			IContact contact = contactService.getContactById(Long.parseLong(idContact));
			if(contact == null ) {
				contact = entrepriseService.getEntrepriseById(Long.parseLong(idContact));
			}
			RequestDispatcher rd = request.getRequestDispatcher("CreateContact.jsp");
			request.setAttribute("contact", contact);
			rd.forward(request, response);
		}else if(modeDelete != null && idContact.length() > 0){	/* mode delete */
			IContact contact = contactService.getContactById(Long.parseLong(idContact));
			if(contact == null ) {
				contact = entrepriseService.getEntrepriseById(Long.parseLong(idContact));
			}
			request.setAttribute("idcontact", contact.getIdContact());
			RequestDispatcher rd = request.getRequestDispatcher("DeleteServlet");
			rd.forward(request, response);
		}
		
		
	}

}