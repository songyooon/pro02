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
import kr.co.myshop.ctrl.UpdateQnaCtrl;
import kr.co.myshop.vo.Qna;

@WebServlet({"/UpdateQnaCtrl.do"})
public class UpdateQnaCtrl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int no = Integer.parseInt(request.getParameter("no"));
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from qnaa where no=?";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      pstmt.setInt(1, no);
      ResultSet rs = pstmt.executeQuery();
      Qna vo = new Qna();
      if (rs.next()) {
        vo.setNo(rs.getInt("no"));
        vo.setTitle(rs.getString("title"));
        vo.setContent(rs.getString("content"));
        vo.setAuthor(rs.getString("author"));
        vo.setResDate(rs.getDate("resdate"));
        vo.setLev(rs.getInt("lev"));
        vo.setParno(rs.getInt("parno"));
        vo.setSec(rs.getString("sec"));
        vo.setVisited(rs.getInt("visited"));
      } 
      request.setAttribute("qna", vo);
      RequestDispatcher view = request.getRequestDispatcher("./qna/updateQna.jsp");
      view.forward((ServletRequest)request, (ServletResponse)response);
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
