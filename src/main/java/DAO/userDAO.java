package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Base.DatabaseConf;
import Base.user;

public class userDAO implements DatabaseConf{
	
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
	public List<user> getAll(int page){
		List<user> t_user = new ArrayList<user>();
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			int tot = total(); 
			conn = DriverManager.getConnection(JDBCURL,User,password);
			stmt = conn.prepareStatement( "SELECT * FROM t_user limit ?, ?");
			
			if(page * 10 < tot)
			{
				stmt.setInt(1, (page-1) * 10);
				stmt.setInt(2, (page) * 10);
			} else {
				stmt.setInt(1, (page-1) * 10);
				stmt.setInt(2, tot);
			}

			//定义数据库操作SQL语句:查询t_user表所有记录和所有字段
			//创建数据库操作申明
			//执行数据库查询，返回结果集
			user us = null;
			//对结果集进行遍历，
			rst = stmt.executeQuery();
			while(rst.next()){
				//将每条数据封装成一个新的user对象，一定要new
				us = new user();
				us.setId(rst.getInt("id"));
				us.setName(rst.getString("name"));
				us.setUsername(rst.getString("username"));
				us.setPwd(rst.getString("password"));
				us.setEmail(rst.getString("email"));
				us.setUserType(rst.getString("userType"));
				//将user对象保存到List集合中
				t_user.add(us);
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
		return t_user;		
	}
	

	/**
	 * 添加user记录
	 * @param user
	 * @return
	 */
	public int add(user user){
		int result = 0;
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			conn = DriverManager.getConnection(JDBCURL,User,password);
			
			//Check duplicate
			//定义数据库新增语句
			stmt = conn.prepareStatement("INSERT INTO t_user(name, userType, password, email, username) VALUES(?,?,?,?,?)");
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getUserType());
			stmt.setString(3, user.getPwd());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getUsername());

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

	public int del(int id){
		int result = 0;
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			conn = DriverManager.getConnection(JDBCURL,User,password);
			
			//Check duplicate
			//定义数据库新增语句
			stmt = conn.prepareStatement("select count(*) from t_user where id=?");
			stmt.setInt(1, id);

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
			conn = DriverManager.getConnection(JDBCURL,User,password);
			
			//Check duplicate
			//定义数据库新增语句
			stmt = conn.prepareStatement("select count(*) from t_user where name=?");
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
			conn = DriverManager.getConnection(JDBCURL,User,password);
			
			//Check duplicate
			//定义数据库新增语句
			stmt = conn.prepareStatement("select count(*) from t_user");

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
		userDAO userDao = new userDAO();	
		//删除测试
		System.out.println(userDao.total());
	}
}
