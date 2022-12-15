package kr.co.myshop.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.myshop.view.CartToSaleCtrl;
import kr.co.myshop.vo.Product;

@WebServlet({"/CartToSaleCtrl"})
public class CartToSaleCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int proNo = Integer.parseInt(request.getParameter("proNo"));
    int cartNo = Integer.parseInt(request.getParameter("cartNo"));
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      this.sql = "select a.prono, a.cateno, a.proname, a.prospec, a.oriprice, ";
      this.sql = String.valueOf(this.sql) + "a.discountrate, a.propic, a.propic2, b.amount from ";
      this.sql = String.valueOf(this.sql) + "product a right join wearing b on a.prono=b.prono ";
      this.sql = String.valueOf(this.sql) + "where a.prono in (select b.prono from wearing) and ";
      this.sql = String.valueOf(this.sql) + "a.prono=?";
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setInt(1, proNo);
      ResultSet rs = pstmt.executeQuery();
      Product vo = new Product();
      if (rs.next()) {
        vo.setProNo(rs.getInt("prono"));
        vo.setCateNo(rs.getInt("cateno"));
        vo.setProName(rs.getString("proname"));
        vo.setProSpec(rs.getString("prospec"));
        vo.setOriPrice(rs.getInt("oriprice"));
        vo.setDiscountRate(rs.getDouble("discountrate"));
        vo.setProPic(rs.getString("propic"));
        vo.setProPic2(rs.getString("propic2"));
        vo.setAmount(rs.getInt("amount"));
      } 
      request.setAttribute("pro", vo);
      request.setAttribute("cartNo", Integer.valueOf(cartNo));
      RequestDispatcher view = request.getRequestDispatcher("./cart/cartToSale.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
