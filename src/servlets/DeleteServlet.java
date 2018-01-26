package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import entities.ContactGroup;
import entities.IContact;
import entities.PhoneNumber;
import services.AddressService;
import services.ContactGroupService;
import services.IContactService;
import services.PhoneNumberService;


/**
 * Servlet implementation class CreateServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
		// TODO Faire des checks
		String contact = request.getParameter("idcontact");
		System.out.println(request.getParameter("recherche"));
			
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		IContactService contactService = (IContactService) context.getBean("beanContactService");
		AddressService addressService = (AddressService) context.getBean("beanAddressService");
		PhoneNumberService phoneNumberService = (PhoneNumberService) context.getBean("beanPhoneNumberService");
		ContactGroupService contactGroupService = (ContactGroupService) context.getBean("beanContactGroupService");
		
		IContact c = contactService.getContactById(Long.parseLong(contact));
		
		for(PhoneNumber pb : c.getPhones()) {
			phoneNumberService.deletePhoneNumberById(pb.getIdPhoneNumber());
		}
		
		contactService.deleteContact(c.getIdContact());
		for(ContactGroup cg : c.getBooks()){
			contactGroupService.deleteContactInGroup(cg.getIdContactGroup(), c.getIdContact());
		}
		addressService.deleteAddress(c.getAdd().getIdAddress());
		System.out.println("Byebye : "+c.getFirstName());
	
		RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
		rd.forward(request, response);
	}

}
