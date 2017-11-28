package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Base.group;
import DAO.groupDAO;

/**
 * Servlet implementation class doGroupUpdate
 */
@WebServlet("/updateGroup")
public class doGroupUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doGroupUpdate() {
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
		request.setCharacterEncoding("UTF-8");
		//获取aritcleUpdate.jsp传来的session
		HttpSession session = request.getSession();
		int groupid = (int)session.getAttribute("group_id");
		groupDAO groupDao = new groupDAO();    		
		group groupVO = new group();
		groupVO = groupDao.getByid(groupid);

		String groupName = request.getParameter("groupName");
		
		

		String mes = "";
		if(!(groupName.isEmpty())) {
			System.out.println("Hi");
			groupVO.setGroupName(groupName);
			System.out.println(groupVO.toString());
			if(groupDao.update(groupVO) > 0){
				mes = "修改成功！";
				request.setAttribute("MES", mes);
				request.getRequestDispatcher("group-list.jsp").forward(request, response);	
			} else {
				mes = "修改失败！";
				request.setAttribute("MES", mes);
	
			}
		}else {
			mes = "非法输入！请重新输入注册信息";
			request.setAttribute("MES", mes);
			
		}
	}

}
