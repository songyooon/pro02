package kr.co.myshop.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.myshop.view.GetAdminParselListCtrl;
import kr.co.myshop.vo.Parsel;

@WebServlet({"/GetAdminParselListCtrl"})
public class GetAdminParselListCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from parsel order by parselstate asc, parselno asc";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      ResultSet rs = pstmt.executeQuery();
      List<Parsel> parList = new ArrayList<>();
      while (rs.next()) {
        Parsel vo = new Parsel();
        vo.setParselNo(rs.getInt("parselno"));
        vo.setParselAddr(rs.getString("parseladdr"));
        vo.setCusTel(rs.getString("custel"));
        vo.setParselCompany(rs.getString("parselcompany"));
        vo.setParselTel(rs.getString("parseltel"));
        vo.setParselState(rs.getInt("parselstate"));
        vo.setBaleCode(rs.getString("balecode"));
        parList.add(vo);
      } 
      request.setAttribute("parList", parList);
      RequestDispatcher view = request.getRequestDispatcher("./parsel/parselList.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
