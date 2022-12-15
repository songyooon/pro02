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
import kr.co.myshop.view.GetCustomListCtrl;
import kr.co.myshop.vo.Custom;

@WebServlet({"/GetCustomListCtrl"})
public class GetCustomListCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from custom order by regdate desc";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      ResultSet rs = pstmt.executeQuery();
      List<Custom> cusList = new ArrayList<>();
      while (rs.next()) {
        Custom vo = new Custom();
        vo.setCusId(rs.getString("cusid"));
        vo.setCusPw(rs.getString("cuspw"));
        vo.setCusName(rs.getString("cusname"));
        vo.setAddress(rs.getString("address"));
        vo.setTel(rs.getString("tel"));
        vo.setRegDate(rs.getString("regdate"));
        vo.setPoint(rs.getInt("point"));
        vo.setLevel(rs.getInt("level"));
        vo.setVisited(rs.getInt("visited"));
        cusList.add(vo);
      } 
      request.setAttribute("cusList", cusList);
      RequestDispatcher view = request.getRequestDispatcher("./admin/customList.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
