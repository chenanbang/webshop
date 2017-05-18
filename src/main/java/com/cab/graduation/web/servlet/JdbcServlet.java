package com.cab.graduation.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JdbcServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String url="jdbc:mysql://localhost:3306/graduationdb?useUnicode=true&amp;characterEncoding=utf-8";
	
	private static final String user="db_user";
	
	private static final String password="123456";

	static {
		//1、加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

		try {
			Connection con = DriverManager.getConnection(url, user, password);
			if(!con.isClosed()){
				System.out.println("连接成功!");
				response.getWriter().print("database connected!");
			}
		} catch (SQLException e) {
			response.getWriter().print("database disconnect!");
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			if(!con.isClosed()){
				System.out.println("连接成功!");
			}
		} catch (SQLException e) {
			System.out.println("连接失败!");
			e.printStackTrace();
		}
	}
	
}
