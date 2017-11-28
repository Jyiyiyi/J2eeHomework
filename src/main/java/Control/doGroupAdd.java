package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Base.group;
import Base.user2group;
import DAO.groupDAO;
import DAO.user2groupDAO;

/**
 * Servlet implementation class doGroupAdd
 */
@WebServlet("/addGroup")
public class doGroupAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doGroupAdd() {
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
		// TODO Auto-generated method stub
		String gname = request.getParameter("groupname");
		int leader = Integer.parseInt(request.getParameter("leader"));
		HttpSession session = request.getSession();
		
		groupDAO gdao = new groupDAO();
		user2groupDAO u2gdao = new user2groupDAO();
		
		group g = new group();
		g.setGroupName(gname);
		gdao.add(g);
		
		g = gdao.getByname(gname);
		user2group u2g = new user2group();
		u2g.setDescription("leader");
		u2g.setGid(g.getId());
		u2g.setUid(leader);
		u2g.setLeader("1");
		u2gdao.add(u2g);
		
	}

}
