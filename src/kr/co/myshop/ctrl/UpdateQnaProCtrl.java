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

@WebServlet({"/UpdateQnaProCtrl.do"})
public class UpdateQnaProCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    int no = Integer.parseInt(request.getParameter("no"));
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String author = request.getParameter("author");
    String sec = request.getParameter("sec");
    int lev = Integer.parseInt(request.getParameter("lev"));
    String sql = "";
    int cnt = 0;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      sql = "update qnaa set title=?, content=?, author=?, sec=?, lev=? where no=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, title);
      pstmt.setString(2, content);
      pstmt.setString(3, author);
      pstmt.setString(4, sec);
      pstmt.setInt(5, lev);
      pstmt.setInt(6, no);
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
