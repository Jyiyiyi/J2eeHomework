package Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDAO;

/**
 * Servlet implementation class list
 */
public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public list() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		BookDAO dao = new BookDAO();
		int pageT = dao.total();
		if(request.getParameter("page") != null)
		{
			request.setAttribute("items", dao.getAll(Integer.parseInt(request.getParameter("page"))));
			request.setAttribute("page", request.getParameter("page"));
		}
		else
		{
			request.setAttribute("items", dao.getAll(1));
			request.setAttribute("page", 1);
		}

		if(pageT % 10 == 0)
			request.setAttribute("total", String.valueOf(pageT / 10));
		else
			request.setAttribute("total", String.valueOf(pageT / 10 + 1));
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
