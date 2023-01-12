package Masterarbeit.my_app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "Servlet zum Registrieren neuer Nutzer", urlPatterns = { "/Register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
//		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try 
		{
			User user = new User();
			user.setUsername(username);
			user.encryptP(password);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Subject currentUser = SecurityUtils.getSubject();
				if(user.createUser())
				{
					request.setAttribute("username", username);
					request.setAttribute("password", password);
					if ( !currentUser.isAuthenticated() ) {
					    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
					    token.setRememberMe(true);
					    currentUser.login(token);
					}
					response.sendRedirect("/my-app-0.0.1-SNAPSHOT/home.jsp");
				}
				else 
				{
					response.sendRedirect("/my-app-0.0.1-SNAPSHOT/register.jsp?s=f");
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
