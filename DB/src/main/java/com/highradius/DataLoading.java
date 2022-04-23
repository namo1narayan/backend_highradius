package com.highradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class DataLoading
 */
@WebServlet("/DataLoading")
public class DataLoading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataLoading() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<Object,Object> Response = new HashMap<Object,Object>();
		ArrayList<Winter_Internship> Winter_Internship = new ArrayList<Winter_Internship>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose","root","MySQL@#123");
			PreparedStatement ps=connection.prepareStatement("select *from winter_internship limit ?");
			ps.setInt(1,5);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Winter_Internship winter_internship= new Winter_Internship(rs.getInt("sl_no"),rs.getString("business_code"),
						rs.getString("cust_number"),rs.getString("clear_date"),rs.getInt("buisness_year"),rs.getString("doc_id"),rs.getString("posting_date"),
						rs.getString("document_create_date"),rs.getString("document_create_date1"),rs.getString("due_in_date"),rs.getString("invoice_currency"),rs.getString("document_type"),rs.getInt("posting_id"),rs.getString("area_business"),rs.getDouble("total_open_amount"),rs.getString("baseline_create_date"),rs.getString("cust_payment_terms"),
						rs.getInt("invoice_id"),rs.getInt("isOpen"),rs.getString("aging_bucket"),rs.getInt("is_deleted"));
				Winter_Internship.add(winter_internship);
			}
			Response.put("winter_internship",Winter_Internship);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(Response);
		response.setHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
