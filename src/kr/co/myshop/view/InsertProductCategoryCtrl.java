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
import kr.co.myshop.view.InsertProductCategoryCtrl;
import kr.co.myshop.vo.Category;

@WebServlet({"/InsertProductCategoryCtrl"})
public class InsertProductCategoryCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from category";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      ResultSet rs = pstmt.executeQuery();
      List<Category> cateList = new ArrayList<>();
      while (rs.next()) {
        Category vo = new Category();
        vo.setCateNo(rs.getInt("cateno"));
        vo.setCateName(rs.getString("catename"));
        cateList.add(vo);
      } 
      request.setAttribute("cateList", cateList);
      RequestDispatcher view = request.getRequestDispatcher("./product/insertProduct.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
