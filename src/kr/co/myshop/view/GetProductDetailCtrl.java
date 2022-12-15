package kr.co.myshop.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.myshop.vo.Product;

@WebServlet("/GetProductDetailCtrl")
public class GetProductDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	String sql = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int proNo = Integer.parseInt(request.getParameter("proNo"));
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);		
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			sql = "update product set scnt=scnt+1 where prono=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, proNo);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "select a.prono, a.cateno, a.proname, a.prospec, a.oriprice, ";			
			sql = sql + "a.discountrate, a.propic, a.propic2, b.amount from ";
			sql = sql + "product a right join wearing b on a.prono=b.prono ";
			sql = sql + "where a.prono in (select b.prono from wearing) and ";
			sql = sql + "a.prono=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, proNo);
			ResultSet rs = pstmt.executeQuery();
			
			//결과를 데이터베이스로 부터 받아서 VO에 저장
			Product vo = new Product();
			if(rs.next()){
				vo.setProNo(rs.getInt("prono"));
				vo.setCateNo(rs.getInt("cateno"));
				vo.setProName(rs.getString("proname"));
				vo.setProSpec(rs.getString("prospec"));
				vo.setOriPrice(rs.getInt("oriprice"));
				vo.setDiscountRate(rs.getDouble("discountrate"));
				vo.setProPic(rs.getString("propic"));
				vo.setProPic2(rs.getString("propic2"));
				vo.setAmount(rs.getInt("amount"));
			} else {
				rs.close();
				pstmt.close();
				pstmt = null;
				rs = null;
				sql = "select * from product where prono=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, proNo);
				rs = pstmt.executeQuery();
				if(rs.next()){
					vo.setProNo(rs.getInt("prono"));
					vo.setCateNo(rs.getInt("cateno"));
					vo.setProName(rs.getString("proname"));
					vo.setProSpec(rs.getString("prospec"));
					vo.setOriPrice(rs.getInt("oriprice"));
					vo.setDiscountRate(rs.getDouble("discountrate"));
					vo.setProPic(rs.getString("propic"));
					vo.setProPic2(rs.getString("propic2"));
					vo.setAmount(0);
				}
			}
			request.setAttribute("pro", vo);
			
			//product/productDetail.jsp 에 포워딩
			RequestDispatcher view = request.getRequestDispatcher("./product/productDetail.jsp");
			view.forward(request, response);
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}