package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.groupDAO;

/**
 * Servlet implementation class doGroupDelete
 */
@WebServlet("/deleteGroup")
public class doGroupDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	groupDAO groupDao = new groupDAO();    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doGroupDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupid = request.getParameter("id");

		String mes = "";
		if(groupid != null){
			int id = Integer.parseInt(groupid);
			
			if(groupDao.del(id) > 0){
				mes = "删除成功!";
			} else {
				mes = "删除失败!";
			}
		} else {
			mes = "无该组";
		}
		request.setAttribute("MES", mes);
		response.sendRedirect("group-list.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
