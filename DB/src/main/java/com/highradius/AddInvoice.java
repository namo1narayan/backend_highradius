package com.highradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class AddInvoice
 */
@WebServlet("/AddInvoice")
public class AddInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			HashMap<Object,Object> Response = new HashMap<Object,Object>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose","root","MySQL@#123");
			String BusinessCode =  request.getParameter("business_code");
			String customerNumber =  request.getParameter("cust_number");
			String ClearDate =  request.getParameter("clear_date");
			String BusinessYear =  request.getParameter("buisness_year");
			String DocumentId =  request.getParameter("doc_id");
			String PostingDate =  request.getParameter("posting_date");
			String DocumentCreateDate =  request.getParameter("document_create_date");
			String DueDate =  request.getParameter("due_in_date");
			String InvoiceCurrency =  request.getParameter("invoice_currency");
			String DocumentType =  request.getParameter("document_type");
			String PostingId =  request.getParameter("posting_id");
			String TotalOpenAmount = request.getParameter("total_open_amount");
			String BaselineCreateDate =  request.getParameter("baseline_create_date");
			String CustPaymentTerms =  request.getParameter("cust_payment_terms");
			String InvoiceId = request.getParameter("invoice_id");
			
			
			String query = "INSERT INTO winter_internship (business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,"
					+ "document_create_date,due_in_date,invoice_currency,document_type,posting_id,total_open_amount,baseline_create_date,cust_payment_terms, invoice_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, BusinessCode);
			ps.setString(2, customerNumber);
			ps.setString(3, ClearDate);
			ps.setString(4, BusinessYear);
			ps.setString(5, DocumentId);
			ps.setString(6, PostingDate);
			ps.setString(7, DocumentCreateDate);
			ps.setString(8, DueDate);
			ps.setString(9, InvoiceCurrency);
			ps.setString(10, DocumentType);
			ps.setString(11, PostingId);
			ps.setString(12, TotalOpenAmount);
			ps.setString(13, BaselineCreateDate);
			ps.setString(14, CustPaymentTerms);
			ps.setString(15, InvoiceId);
			
			if(ps.executeUpdate() >0) {
				Response.put("insert", true);
			}else {
				Response.put("insert", false);
			}
			Gson gson = new Gson();
			String JSONresponse= gson.toJson(Response);
			response.setHeader("Access-Control-Allow-Origin","*");
			response.getWriter().append(JSONresponse);
			

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}
