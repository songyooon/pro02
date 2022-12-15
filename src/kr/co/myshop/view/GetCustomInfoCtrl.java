package kr.co.myshop.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.co.myshop.vo.Custom;

@WebServlet({"/GetCustomInfoCtrl"})
public class GetCustomInfoCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    HttpSession session = request.getSession();
    String cusId = (String)session.getAttribute("sid");
    String sql = "";
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      sql = "select * from custom where cusid=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = null;
      pstmt.setString(1, cusId);
      rs = pstmt.executeQuery();
      Custom vo = new Custom();
      if (rs.next()) {
        vo.setCusId(rs.getString("cusid"));
        vo.setCusPw(rs.getString("cuspw"));
        vo.setCusName(rs.getString("cusname"));
        vo.setAddress(rs.getString("address"));
        vo.setTel(rs.getString("tel"));
        vo.setRegDate(rs.getString("regdate"));
        vo.setPoint(rs.getInt("point"));
        vo.setLevel(rs.getInt("level"));
        vo.setVisited(rs.getInt("visited"));
      } else {
        response.sendRedirect("./custom/login.jsp");
      } 
      request.setAttribute("custom", vo);
      RequestDispatcher view = request.getRequestDispatcher("./custom/customInfo.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
