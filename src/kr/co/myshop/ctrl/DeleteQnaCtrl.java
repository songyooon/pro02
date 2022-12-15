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

@WebServlet({"/DeleteQnaCtrl.do"})
public class DeleteQnaCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    String no = request.getParameter("no");
    String parno = request.getParameter("parno");
    String sql = "";
    int cnt = 0;
    int tranNo = 0;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      if (no != null) {
        tranNo = Integer.parseInt(no);
        sql = "delete from qnaa where no=?";
      } else {
        tranNo = Integer.parseInt(parno);
        sql = "delete from qnaa where parno=?";
      } 
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, tranNo);
      cnt = pstmt.executeUpdate();
      if (cnt >= 1) {
        response.sendRedirect("GetQnaListCtrl.do");
      } else {
        response.sendRedirect("GetQnaDetailCtrl.do?no=" + no);
      } 
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
