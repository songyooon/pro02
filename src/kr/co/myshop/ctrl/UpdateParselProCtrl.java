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
import kr.co.myshop.ctrl.UpdateParselProCtrl;

@WebServlet({"/UpdateParselProCtrl"})
public class UpdateParselProCtrl extends HttpServlet {
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
    int parselNo = Integer.parseInt(request.getParameter("parselNo"));
    String parselCompany = request.getParameter("parselCompany");
    String baleCode = request.getParameter("baleCode");
    String parselTel = request.getParameter("parselTel");
    int parselState = Integer.parseInt(request.getParameter("parselState"));
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "update parsel set parselcompany=?, balecode=?, parseltel=?, parselstate=? where parselno=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setString(1, parselCompany);
      pstmt.setString(2, baleCode);
      pstmt.setString(3, parselTel);
      pstmt.setInt(4, parselState);
      pstmt.setInt(5, parselNo);
      this.cnt = pstmt.executeUpdate();
      if (this.cnt >= 1) {
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/admin/index.jsp");
      } else {
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/UpdateParselCtrl?parselNo=" + parselNo);
      } 
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
