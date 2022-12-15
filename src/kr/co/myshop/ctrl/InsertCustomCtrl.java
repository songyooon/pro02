package kr.co.myshop.ctrl;

import com.crypto.util.SHA256;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.myshop.ctrl.InsertCustomCtrl;

@WebServlet({"/InsertCustomCtrl"})
public class InsertCustomCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  int cnt = 0;
  
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    String cusId = request.getParameter("cusId");
    String cus = request.getParameter("cusPw");
    String cusPw = "";
    try {
      cusPw = SHA256.encrypt(cus);
    } catch (NoSuchAlgorithmException e1) {
      e1.printStackTrace();
    } 
    String cusName = request.getParameter("cusName");
    String address1 = request.getParameter("address1");
    String address2 = request.getParameter("address2");
    String address = String.valueOf(address1) + " " + address2;
    String tel = request.getParameter("tel");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "insert into custom(cusid, cuspw, cusname, address, tel) values (?,?,?,?,?)";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setString(1, cusId);
      pstmt.setString(2, cusPw);
      pstmt.setString(3, cusName);
      pstmt.setString(4, address);
      pstmt.setString(5, tel);
      this.cnt = pstmt.executeUpdate();
      if (this.cnt >= 1) {
        response.sendRedirect("./custom/login.jsp");
      } else {
        response.sendRedirect("./custom/insertCustom.jsp");
      } 
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
