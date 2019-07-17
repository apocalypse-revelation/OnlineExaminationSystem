package cn.big.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC连接Oracle数据库的示例代码
 * */

public class DBUtil{
	static
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();// 加载Oracle驱动程序
			//System.out.println("oracle驱动程序加载中！");
		}
		catch(InstantiationException e1)
		{
			System.out.println("实例异常");
		}
		catch(IllegalAccessException e2)
		{
			System.out.println("访问异常");
		}
		catch(ClassNotFoundException e3)
		{
			System.out.println("MySQL驱动类找不到");
		}
	}
	
	/***
	 * 返回一个数据库连接
	 */ 	
	public static Connection getConnection()
	{
	    Connection connection = null;// 创建一个数据库连接
	    try
	    {
	        //System.out.println("开始尝试连接数据库！");
//	        String url = "jdbc:oracle:thin:@127.0.0.1:1521:oracle";//Oracle的默认数据库名
	        String url = "jdbc:oracle:thin:@localhost:1521:orcl";//Oracle的默认数据库名
	        String user = "system";// 系统默认的用户名
	        String password = "maybe";// 安装时设置的密码
	        connection = DriverManager.getConnection(url, user, password);// 获取连接
//	        System.out.println(url);
//	        System.out.println("用户名："+user+"\t"+"密码：******");
//	        System.out.println("数据库连接成功！");
	        return connection;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        return null;
	    }
	}
}
