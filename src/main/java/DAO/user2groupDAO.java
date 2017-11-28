package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Base.DatabaseConf;
import Base.user2group;

public class user2groupDAO implements DatabaseConf{
	
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
	public List<user2group> getAll(){
		List<user2group> user2groups = new ArrayList<user2group>();
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			int tot = total(); 
			conn = DriverManager.getConnection(JDBCURL,User,password);
			stmt = conn.prepareStatement( "SELECT * FROM t_usergroups");
			
			//定义数据库操作SQL语句:查询t_user2group表所有记录和所有字段
			//创建数据库操作申明
			//执行数据库查询，返回结果集
			user2group us = null;
			//对结果集进行遍历，
			rst = stmt.executeQuery();
			while(rst.next()){
				//将每条数据封装成一个新的user2group对象，一定要new
				us = new user2group();
				us.setId(rst.getInt("id"));
				us.setDescription(rst.getString("description"));
				us.setLeader(rst.getString("leader"));
				us.setUid(rst.getInt("uid"));
				us.setGid(rst.getInt("gid"));
				//将user2group对象保存到List集合中
				user2groups.add(us);
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
		return user2groups;		
	}
	public List<user2group> getLeader(int id){
		List<user2group> user2groups = new ArrayList<user2group>();
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			conn = DriverManager.getConnection(JDBCURL,User,password);
			stmt = conn.prepareStatement( "SELECT * FROM t_usergroups where uid=? and leader=1");
			stmt.setInt(1, id);
			
			//定义数据库操作SQL语句:查询t_user2group表所有记录和所有字段
			//创建数据库操作申明
			//执行数据库查询，返回结果集
			user2group us = null;
			//对结果集进行遍历，
			rst = stmt.executeQuery();
			while(rst.next()){
				//将每条数据封装成一个新的user2group对象，一定要new
				us = new user2group();
				us.setId(rst.getInt("id"));
				us.setDescription(rst.getString("description"));
				us.setLeader(rst.getString("leader"));
				us.setUid(rst.getInt("uid"));
				us.setGid(rst.getInt("gid"));
				//将user2group对象保存到List集合中
				user2groups.add(us);
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
		return user2groups;		
	}
	public List<user2group> getByid(int id){
		List<user2group> user2groups = new ArrayList<user2group>();
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			conn = DriverManager.getConnection(JDBCURL,User,password);
			stmt = conn.prepareStatement( "SELECT * FROM t_usergroups where id=?");
			stmt.setInt(1, id);
			
			//定义数据库操作SQL语句:查询t_user2group表所有记录和所有字段
			//创建数据库操作申明
			//执行数据库查询，返回结果集
			user2group us = null;
			//对结果集进行遍历，
			rst = stmt.executeQuery();
			while(rst.next()){
				//将每条数据封装成一个新的user2group对象，一定要new
				us = new user2group();
				us.setId(rst.getInt("id"));
				us.setDescription(rst.getString("description"));
				us.setLeader(rst.getString("leader"));
				us.setUid(rst.getInt("uid"));
				us.setGid(rst.getInt("gid"));
				//将user2group对象保存到List集合中
				user2groups.add(us);
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
		return user2groups;		
	}
	public List<user2group> getMem(int id){
		List<user2group> user2groups = new ArrayList<user2group>();
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			conn = DriverManager.getConnection(JDBCURL,User,password);
			stmt = conn.prepareStatement( "SELECT * FROM t_usergroups where uid=? and leader=0");
			stmt.setInt(1, id);
			
			//定义数据库操作SQL语句:查询t_user2group表所有记录和所有字段
			//创建数据库操作申明
			//执行数据库查询，返回结果集
			user2group us = null;
			//对结果集进行遍历，
			rst = stmt.executeQuery();
			while(rst.next()){
				//将每条数据封装成一个新的user2group对象，一定要new
				us = new user2group();
				us.setId(rst.getInt("id"));
				us.setDescription(rst.getString("description"));
				us.setLeader(rst.getString("leader"));
				us.setUid(rst.getInt("uid"));
				us.setGid(rst.getInt("gid"));
				//将user2group对象保存到List集合中
				user2groups.add(us);
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
		return user2groups;		
	}

	/**
	 * 添加user2group记录
	 * @param user2group
	 * @return
	 */
	public int add(user2group user2group){
		int result = 0;
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			conn = DriverManager.getConnection(JDBCURL,User,password);
			
			//Check duplicate
			//定义数据库新增语句
			stmt = conn.prepareStatement("INSERT INTO t_usergroup( leader, description, uid, gid) VALUES(?,?,?,?)");
			stmt.setString(1, user2group.getLeader());
			stmt.setString(2, user2group.getDescription());
			stmt.setInt(3, user2group.getUid());
			stmt.setInt(4, user2group.getGid());

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
			stmt = conn.prepareStatement("select count(*) from t_usergroup where id=?");
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

	public int total() {
		int result = 1;
		try {
			//加载MySQL数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库连接
			conn = DriverManager.getConnection(JDBCURL,User,password);
			
			//Check duplicate
			//定义数据库新增语句
			stmt = conn.prepareStatement("select count(*) from t_usergroup");

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
		user2groupDAO user2groupDao = new user2groupDAO();	
		//删除测试
		System.out.println(user2groupDao.total());
	}
}
