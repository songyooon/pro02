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
import javax.servlet.http.HttpSession;
import kr.co.myshop.view.GetMemberCartListCtrl;
import kr.co.myshop.vo.CartListVO;

@WebServlet({"/GetMemberCartListCtrl"})
public class GetMemberCartListCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String cusId = (String)session.getAttribute("sid");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select a.cartno, a.prono, a.cusid, b.proname, b.cateno, b.prospec, b.oriprice, b.discountrate, b.propic, b.propic2 from cart a inner join product b on a.prono=b.prono where a.cusid=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setString(1, cusId);
      ResultSet rs = pstmt.executeQuery();
      List<CartListVO> cartList = new ArrayList<>();
      while (rs.next()) {
        CartListVO vo = new CartListVO();
        vo.setCartNo(rs.getInt("cartno"));
        vo.setProNo(rs.getInt("prono"));
        vo.setCusId(rs.getString("cusid"));
        vo.setProName(rs.getString("proname"));
        vo.setProSpec(rs.getString("prospec"));
        vo.setOriPrice(rs.getInt("oriPrice"));
        vo.setDiscountRate(rs.getDouble("discountrate"));
        vo.setProPic(rs.getString("propic"));
        vo.setProPic2(rs.getString("propic2"));
        cartList.add(vo);
      } 
      request.setAttribute("cartList", cartList);
      RequestDispatcher view = request.getRequestDispatcher("./cart/cartList.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
