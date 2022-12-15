package kr.co.myshop.ctrl;

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
import kr.co.myshop.ctrl.UpdateParselCtrl;
import kr.co.myshop.vo.Parsel;

@WebServlet({"/UpdateParselCtrl"})
public class UpdateParselCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int parselNo = Integer.parseInt(request.getParameter("parselNo"));
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from parsel where parselno=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setInt(1, parselNo);
      ResultSet rs = pstmt.executeQuery();
      Parsel vo = new Parsel();
      if (rs.next()) {
        vo.setParselNo(rs.getInt("parselno"));
        vo.setParselAddr(rs.getString("parseladdr"));
        vo.setCusTel(rs.getString("custel"));
        vo.setParselCompany(rs.getString("parselcompany"));
        vo.setParselTel(rs.getString("parseltel"));
        vo.setParselState(rs.getInt("parselstate"));
        vo.setBaleCode(rs.getString("balecode"));
      } 
      request.setAttribute("parsel", vo);
      RequestDispatcher view = request.getRequestDispatcher("./parsel/updateParsel.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
