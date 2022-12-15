package kr.co.myshop.ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/UpdateSalesProCtrl"})
public class UpdateSalesProCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    int parselNo = Integer.parseInt(request.getParameter("parselNo"));
    String parselAddr = request.getParameter("parselAddr");
    if (request.getParameter("address1") != null)
      parselAddr = String.valueOf(request.getParameter("address1")) + " " + request.getParameter("address2"); 
    String cusTel = request.getParameter("cusTel");
    String sql = "";
    int cnt = 0;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      sql = "update parsel set parseladdr=?, custel=? where parselno=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, parselAddr);
      pstmt.setString(2, cusTel);
      pstmt.setInt(3, parselNo);
      cnt = pstmt.executeUpdate();
      if (cnt >= 1) {
        response.sendRedirect("GetMemberSalesInfoCtrl");
      } else {
        response.sendRedirect("UpdateSalesCtrl?parselNo=" + parselNo);
      } 
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
