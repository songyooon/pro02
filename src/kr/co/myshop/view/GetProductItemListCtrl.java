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
import kr.co.myshop.view.GetProductItemListCtrl;
import kr.co.myshop.vo.Product;

@WebServlet({"/GetProductItemListCtrl"})
public class GetProductItemListCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int cateNo = Integer.parseInt(request.getParameter("cateNo"));
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from product where cateno=? order by prono";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setInt(1, cateNo);
      ResultSet rs = pstmt.executeQuery();
      List<Product> proList = new ArrayList<>();
      while (rs.next()) {
        Product vo = new Product();
        vo.setProNo(rs.getInt("prono"));
        vo.setCateNo(rs.getInt("cateno"));
        vo.setProName(rs.getString("proname"));
        vo.setProSpec(rs.getString("prospec"));
        vo.setOriPrice(rs.getInt("oriprice"));
        vo.setDiscountRate(rs.getDouble("discountrate"));
        vo.setProPic(rs.getString("propic"));
        vo.setProPic2(rs.getString("propic2"));
        proList.add(vo);
      } 
      request.setAttribute("proList", proList);
      request.setAttribute("cateNo", Integer.valueOf(cateNo));
      RequestDispatcher view = request.getRequestDispatcher("./product/productList.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
