package kr.co.myshop.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import kr.co.myshop.view.GetIndexProductListCtrl;
import kr.co.myshop.vo.Product;

public class GetIndexProductListCtrl {
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  
  private static final String URL = "jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul";
  
  private static final String USER = "root";
  
  private static final String PASS = "a1234";
  
  String sql = "";
  
  public ArrayList<Product> bestProduct() {
    ArrayList<Product> proList = new ArrayList<>();
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from product where prono in (select * from (select prono from sales group by prono order by sum(amount) desc limit 4) as tot)";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        Product vo = new Product();
        vo.setProNo(rs.getInt("prono"));
        vo.setCateNo(rs.getInt("cateno"));
        vo.setProName(rs.getString("proname"));
        vo.setProSpec(rs.getString("prospec"));
        vo.setOriPrice(rs.getInt("oriprice"));
        vo.setDiscountRate(rs.getDouble("discountrate"));
        vo.setProPic(rs.getString("propic"));
        vo.setProPic(rs.getString("propic2"));
        proList.add(vo);
      } 
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return proList;
  }
  
  public ArrayList<Product> newProduct() {
    ArrayList<Product> proList = new ArrayList<>();
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from product order by regdate desc limit 4";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        Product vo = new Product();
        vo.setProNo(rs.getInt("prono"));
        vo.setCateNo(rs.getInt("cateno"));
        vo.setProName(rs.getString("proname"));
        vo.setProSpec(rs.getString("prospec"));
        vo.setOriPrice(rs.getInt("oriprice"));
        vo.setDiscountRate(rs.getDouble("discountrate"));
        vo.setProPic(rs.getString("propic"));
        vo.setProPic(rs.getString("propic2"));
        proList.add(vo);
      } 
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return proList;
  }
  
  public ArrayList<Product> hotProduct() {
    ArrayList<Product> proList = new ArrayList<>();
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.sql = "select * from product order by scnt desc limit 4";
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Seoul", "root", "a1234");
      PreparedStatement pstmt = con.prepareStatement(this.sql);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        Product vo = new Product();
        vo.setProNo(rs.getInt("prono"));
        vo.setCateNo(rs.getInt("cateno"));
        vo.setProName(rs.getString("proname"));
        vo.setProSpec(rs.getString("prospec"));
        vo.setOriPrice(rs.getInt("oriprice"));
        vo.setDiscountRate(rs.getDouble("discountrate"));
        vo.setProPic(rs.getString("propic"));
        vo.setProPic(rs.getString("propic2"));
        proList.add(vo);
      } 
      rs.close();
      pstmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return proList;
  }
}
