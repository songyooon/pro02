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
import kr.co.myshop.ctrl.InsertQnaProCtrl;

@WebServlet({"/InsertQnaProCtrl.do"})
public class InsertQnaProCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  int cnt = 0;
  
  ResultSet rs = null;
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String author = request.getParameter("author");
    String sec = request.getParameter("sec");
    int lev = Integer.parseInt(request.getParameter("lev"));
    int no = 0;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      this.sql = "insert into qnaa(title, content, author, sec, lev) values (?,?,?,?,?)";
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setString(1, title);
      pstmt.setString(2, content);
      pstmt.setString(3, author);
      pstmt.setString(4, sec);
      pstmt.setInt(5, lev);
      this.cnt = pstmt.executeUpdate();
      pstmt.close();
      this.sql = "select no from qnaa order by resdate desc limit 1";
      pstmt = con.prepareStatement(this.sql);
      this.rs = pstmt.executeQuery();
      if (this.rs.next())
        no = this.rs.getInt("no"); 
      this.rs.close();
      pstmt.close();
      System.out.println(": " + no);
      this.sql = "update qnaa set parno=? where no=?";
      pstmt = con.prepareStatement(this.sql);
      pstmt.setInt(1, no);
      pstmt.setInt(2, no);
      pstmt.executeUpdate();
      pstmt.close();
      con.close();
      if (this.cnt >= 1) {
        response.sendRedirect("GetQnaListCtrl.do");
      } else {
        response.sendRedirect("./qna/qnaWrite.jsp");
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
