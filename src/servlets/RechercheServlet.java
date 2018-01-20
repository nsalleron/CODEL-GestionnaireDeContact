package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Contact;
import services.ContactService;

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
		
		System.out.println("----> idContact : "+idContact);
		
		if(idContact == null) {
			ArrayList<Contact> contacts = ContactService.researchContacts(recherche);
			RequestDispatcher rd = request.getRequestDispatcher("UpdateContact.jsp");
			request.setAttribute("contacts", contacts);
			request.setAttribute("recherche", recherche);
			rd.forward(request, response);
		}else {
			Contact contact = ContactService.getContactById(Long.parseLong(idContact));
			System.out.println("RechercheServlet : VERSION ---> "+contact.getVersion());
			RequestDispatcher rd = request.getRequestDispatcher("CreateContact.jsp");
			request.setAttribute("contact", contact);
			rd.forward(request, response);
		}
		
		
	}

}