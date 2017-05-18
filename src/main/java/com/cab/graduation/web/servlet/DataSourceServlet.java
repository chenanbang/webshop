package com.cab.graduation.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

public class DataSourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static ApplicationContext applicationContext=null;
	private static DataSource dataSource=null;
	static{
		applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		dataSource=applicationContext.getBean(DataSource.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Connection con=dataSource.getConnection();
			if(!con.isClosed()){
				response.getWriter().println("connected:"+con.getClass());
				
			}
		} catch (SQLException e) {
			response.getWriter().println("disconnected");
			response.getWriter().println("exception:"+e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static void main(String[] args) throws SQLException {
		Connection con=dataSource.getConnection();
		System.out.println(con);
	}
	
}
