package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
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
		// TODO Auto-generated method stub
		String user = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		
		System.out.print("USER : "+ user+"\n");
		System.out.print("PWD : "+ pwd+"\n");
		
		
		if(user.equals(pwd)) {
			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			request.setAttribute("user", user);
			request.setAttribute("pwd", pwd);
			request.getSession().setAttribute("name", user);
			rd.forward(request, response);
		}else if(request.getParameter("Reset") != null) {
			response.sendRedirect("index.html");
		}else{
			response.sendRedirect("index.html");
		}
		
		
	}

}
