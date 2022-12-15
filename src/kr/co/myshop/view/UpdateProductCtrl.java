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
import kr.co.myshop.view.UpdateProductCtrl;
import kr.co.myshop.vo.Category;
import kr.co.myshop.vo.Product;

@WebServlet({"/UpdateProductCtrl"})
public class UpdateProductCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int proNo = Integer.parseInt(request.getParameter("proNo"));
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from product where prono=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setInt(1, proNo);
      ResultSet rs = pstmt.executeQuery();
      Product vo = new Product();
      if (rs.next()) {
        vo.setProNo(rs.getInt("prono"));
        vo.setCateNo(rs.getInt("cateno"));
        vo.setProName(rs.getString("proname"));
        vo.setProSpec(rs.getString("prospec"));
        vo.setOriPrice(rs.getInt("oriprice"));
        vo.setDiscountRate(rs.getDouble("discountrate"));
        vo.setProPic(rs.getString("propic"));
        vo.setProPic2(rs.getString("propic2"));
      } 
      request.setAttribute("pro", vo);
      rs.close();
      pstmt.close();
      this.sql = "select * from category";
      pstmt = con.prepareStatement(this.sql);
      rs = pstmt.executeQuery();
      List<Category> cateList = new ArrayList<>();
      while (rs.next()) {
        Category cate = new Category();
        cate.setCateNo(rs.getInt("cateno"));
        cate.setCateName(rs.getString("catename"));
        cateList.add(cate);
      } 
      request.setAttribute("cateList", cateList);
      RequestDispatcher view = request.getRequestDispatcher("./product/updateProduct.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
