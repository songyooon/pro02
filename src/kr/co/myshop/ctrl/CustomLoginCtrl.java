package kr.co.myshop.ctrl;

import com.crypto.util.SHA256;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.co.myshop.ctrl.CustomLoginCtrl;

@WebServlet({"/CustomLoginCtrl"})
public class CustomLoginCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    String cusId = request.getParameter("cusId");
    String cus = request.getParameter("cusPw");
    String cusPw = "";
    try {
      cusPw = SHA256.encrypt(cus);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } 
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from custom where cusid=? and cuspw=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      con.setAutoCommit(false);
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      ResultSet rs = null;
      pstmt.setString(1, cusId);
      pstmt.setString(2, cusPw);
      rs = pstmt.executeQuery();
      HttpSession session = request.getSession();
      if (rs.next()) {
        this.sql = "update custom set visited=visited+1, point=point+5 where cusid=?";
        pstmt = con.prepareStatement(this.sql);
        pstmt.setString(1, cusId);
        pstmt.executeUpdate();
        con.commit();
        con.setAutoCommit(true);
        session.setAttribute("sid", cusId);
        session.setAttribute("sname", rs.getString("cusname"));
        response.sendRedirect("index.jsp");
      } else {
        response.sendRedirect("./custom/login.jsp");
      } 
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
