package Masterarbeit.my_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateGroupServlet
 */
@WebServlet("/CreateGroupServlet")
public class CreateGroupServlet extends HttpServlet {

    /**
     * Default constructor. 
     */
    public CreateGroupServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String groupadmin = request.getParameter("groupadmin");
		String grouppassword = request.getParameter("grouppassword");
		String groupname = request.getParameter("groupname");
		PrintWriter out = response.getWriter();
		try
		{
			Group group = new Group();
			group.setGroupadmin(groupadmin);
			group.setGroupname(groupname);
			group.setGrouppassword(grouppassword);
			group.createGroup();
			out.println("Gruppe wurde erstellt.");
			response.sendRedirect("/my-app-0.0.1-SNAPSHOT/home.jsp");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("Da ist etwas schief gelaufen....");
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
