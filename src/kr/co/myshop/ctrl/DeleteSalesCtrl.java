package kr.co.myshop.ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.myshop.ctrl.DeleteSalesCtrl;

@WebServlet({"/DeleteSalesCtrl"})
public class DeleteSalesCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  int parselNo = 0;
  
  int salePayNo = 0;
  
  int amount = 0;
  
  int proNo = 0;
  
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    ResultSet rs = null;
    int saleNo = Integer.parseInt(request.getParameter("saleNo"));
    System.out.println(": " + saleNo);
    String sql = "";
    int cnt = 0;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      con.setAutoCommit(false);
      sql = "select * from sales where saleno=?";
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, saleNo);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        this.parselNo = rs.getInt("parselno");
        this.salePayNo = rs.getInt("salepayno");
        this.amount = rs.getInt("amount");
        this.proNo = rs.getInt("prono");
        System.out.println("parselNo : " + this.parselNo);
        System.out.println("salePayNo : " + this.salePayNo);
        System.out.println("amount : " + this.amount);
        System.out.println("proNo : " + this.proNo);
        sql = "delete from parsel where parselno=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, this.parselNo);
        pstmt.executeUpdate();
        sql = "delete from payment where salepayno=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, this.salePayNo);
        pstmt.executeUpdate();
        sql = "update wearing set amount=amount+? where prono=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, this.amount);
        pstmt.setInt(2, this.proNo);
        pstmt.executeUpdate();
        sql = "delete from sales where saleno=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, saleNo);
        cnt = pstmt.executeUpdate();
        con.commit();
      } 
      con.setAutoCommit(true);
      if (cnt >= 1) {
        response.sendRedirect("GetMemberSalesInfoCtrl");
      } else {
        response.sendRedirect("GetSalesDetailCtrl?saleNo=" + saleNo);
      } 
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
