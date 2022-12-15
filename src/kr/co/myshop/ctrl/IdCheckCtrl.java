package kr.co.myshop.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.myshop.ctrl.IdCheckCtrl;

@WebServlet({"/IdCheckCtrl"})
public class IdCheckCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    String cusId = request.getParameter("cusId");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from custom where cusid=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setString(1, cusId);
      ResultSet rs = pstmt.executeQuery();
      PrintWriter out = response.getWriter();
      String script = "<script>";
      script = String.valueOf(script) + "function apply(fid){";
      script = String.valueOf(script) + "opener.document.frm1.cusId.value = fid;";
      script = String.valueOf(script) + "opener.document.frm1.idck.value = 'yes';";
      script = String.valueOf(script) + "window.close();";
      script = String.valueOf(script) + "}</script>";
      out.println(script);
      if (rs.next()) {
        out.println("<h3>");
        out.println("<h3> <button onclick='javascript:window.close()'>");
      } else {
        out.println("<h3>");
        out.println("<a href='javascript:apply(\"" + cusId + "\")'>" + cusId + "[사용하기]");
        out.println("<p>");
      } 
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
