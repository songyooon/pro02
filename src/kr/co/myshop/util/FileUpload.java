package kr.co.myshop.util;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.myshop.util.FileUpload;

@WebServlet({"/FileUpload"})
public class FileUpload extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  int cnt = 0;
  
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uploadPath = request.getRealPath("/upload");
    System.out.println(uploadPath);
    int size = 10485760;
    int cateNo = 0;
    String proName = "";
    String proSpec = "";
    int oriPrice = 0;
    double discountRate = 0.1D;
    String proPic0 = "";
    String proPic = "";
    String proPic2 = "";
    try {
      MultipartRequest multi = new MultipartRequest(request, 
          uploadPath, 
          size, 
          "UTF-8", 
          (FileRenamePolicy)new DefaultFileRenamePolicy());
      cateNo = Integer.parseInt(multi.getParameter("cateNo"));
      proName = multi.getParameter("proName");
      proSpec = multi.getParameter("proSpec");
      oriPrice = Integer.parseInt(multi.getParameter("oriPrice"));
      discountRate = Double.parseDouble(multi.getParameter("discountRate"));
      
      Enumeration<String> files = multi.getFileNames();
            
      files.nextElement();
      proPic = multi.getParameter("img1");
      
      System.out.println(proPic);
      
      files.nextElement();
      proPic2 = multi.getParameter("img2");
      
      System.out.println(proPic2);
      
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
        sql = "insert into product(cateno, proname, prospec, oriprice, discountrate, propic, propic2) values (?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, cateNo);
        pstmt.setString(2, proName);
        pstmt.setString(3, proSpec);
        pstmt.setInt(4, oriPrice);
        pstmt.setDouble(5, discountRate);
        pstmt.setString(6, proPic);
        pstmt.setString(7, proPic2);
        this.cnt = pstmt.executeUpdate();
        if (this.cnt >= 1) {
          response.sendRedirect("GetProductListCtrl");
        } else {
          response.sendRedirect("InsertProductCategoryCtrl");
        } 
        pstmt.close();
        con.close();
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
