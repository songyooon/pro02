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
import kr.co.myshop.view.GetAdminSalesDetailCtrl;
import kr.co.myshop.vo.Sales;

@WebServlet({"/GetAdminSalesDetailCtrl"})
public class GetAdminSalesDetailCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int saleNo = Integer.parseInt(request.getParameter("saleNo"));
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select a.saleno, a.cusid, a.prono, a.amount, a.saledate, a.parselno, a.salepayno, b.parselstate from sales a inner join parsel b on a.parselno=b.parselno where a.saleno=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      con.setAutoCommit(false);
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setInt(1, saleNo);
      ResultSet rs = pstmt.executeQuery();
      Sales vo = new Sales();
      if (rs.next()) {
        vo.setSaleNo(rs.getInt("saleno"));
        vo.setCusId(rs.getString("cusId"));
        vo.setProNo(rs.getString("prono"));
        vo.setAmount(rs.getInt("amount"));
        vo.setSaleDate(rs.getString("saledate"));
        vo.setParselNo(rs.getInt("parselno"));
        vo.setSalePayNo(rs.getInt("salepayno"));
        vo.setParselState(rs.getInt("parselstate"));
      } 
      request.setAttribute("sales", vo);
      RequestDispatcher view = request.getRequestDispatcher("./admin/salesDetail.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
