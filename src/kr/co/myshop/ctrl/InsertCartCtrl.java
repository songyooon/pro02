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
import javax.servlet.http.HttpSession;
import kr.co.myshop.ctrl.InsertCartCtrl;

@WebServlet({"/InsertCartCtrl"})
public class InsertCartCtrl extends HttpServlet {
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
    HttpSession session = request.getSession();
    int proNo = Integer.parseInt(request.getParameter("proNo"));
    String cusId = (String)session.getAttribute("sid");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "insert into cart(prono, cusid) values (?,?)";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setInt(1, proNo);
      pstmt.setString(2, cusId);
      this.cnt = pstmt.executeUpdate();
      if (this.cnt >= 1) {
        response.sendRedirect("GetProductItemListCtrl?cateNo=3");
      } else {
        response.sendRedirect("GetProductDetailCtrl?proNo=" + proNo);
      } 
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
