package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Base.book;

public class BookDAO {
	String JDBCURL = "jdbc:mysql://localhost:3306/ex1?useUnicode=true&characterEncoding=UTF-8";
	String user = "root";
	String password = "123456"; 
	
	//数据库连接字符串，注意数据库的名称(20150032)和字符编码(UTF-8)
	//数据库连接对象
	private Connection conn = null;
	//数据库操作申明
	private PreparedStatement stmt = null;
	//数据库访问结果集
	private ResultSet rst = null;
	
	/**
	 * 获取所有图书信息
	 * @return
	 */
	public List<book> getAll(int page){
		List<book> users = new ArrayList<book>();
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			int tot = total(); 
			conn = DriverManager.getConnection(JDBCURL,user,password);
			stmt = conn.prepareStatement( "SELECT * FROM books limit ?, ?");
			
			if(page * 10 < tot)
			{
				stmt.setInt(1, (page-1) * 10);
				stmt.setInt(2, (page) * 10);
			} else {
				stmt.setInt(1, (page-1) * 10);
				stmt.setInt(2, tot);
			}

			//定义数据库操作SQL语句:查询t_book表所有记录和所有字段
			//创建数据库操作申明
			//执行数据库查询，返回结果集
			book us = null;
			//对结果集进行遍历，
			rst = stmt.executeQuery();
			while(rst.next()){
				//将每条数据封装成一个新的book对象，一定要new
				us = new book();
				us.setId(rst.getInt("id"));
				us.setName(rst.getString("name"));
				us.setPrice(rst.getDouble("price"));
				//将book对象保存到List集合中
				users.add(us);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{  //一定要使用finally，释放资源
			try {
				stmt.close();  //关闭申明
				conn.close();  //关闭连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//返回结果集
		return users;		
	}
	

	/**
	 * 添加book记录
	 * @param book
	 * @return
	 */
	public int add(book book){
		int result = 0;
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			conn = DriverManager.getConnection(JDBCURL,user,password);
			
			//Check duplicate
			//定义数据库新增语句
			stmt = conn.prepareStatement("INSERT INTO books(name, price) VALUES(?,?)");
			stmt.setString(1, book.getName());
			stmt.setDouble(2, book.getPrice());

			//创建数据库操作申明
			//执行数据库更新操作，返回影响行数
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{  //一定要使用finally，释放资源
			try {
				stmt.close();  //关闭申明
				conn.close();  //关闭连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int check(String name){
		int result = 0;
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			conn = DriverManager.getConnection(JDBCURL,user,password);
			
			//Check duplicate
			//定义数据库新增语句
			stmt = conn.prepareStatement("select count(*) from books where name=?");
			stmt.setString(1, name);

			//创建数据库操作申明
			//执行数据库更新操作，返回影响行数
			rst = stmt.executeQuery();
			if(rst.next())
				result = rst.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{  //一定要使用finally，释放资源
			try {
				stmt.close();  //关闭申明
				conn.close();  //关闭连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int total() {
		int result = 1;
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			conn = DriverManager.getConnection(JDBCURL,user,password);
			
			//Check duplicate
			//定义数据库新增语句
			stmt = conn.prepareStatement("select count(*) from books");

			//创建数据库操作申明
			//执行数据库更新操作，返回影响行数
			rst = stmt.executeQuery();
			if(rst.next())
				result = rst.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{  //一定要使用finally，释放资源
			try {
				stmt.close();  //关闭申明
				conn.close();  //关闭连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		BookDAO bookDao = new BookDAO();	
		//删除测试
		System.out.println(bookDao.check("new"));
	}
}
