package kr.co.myshop.vo;

import kr.co.myshop.vo.Custom;

public class Custom {
  private String cusId;
  
  private String cusPw;
  
  private String cusName;
  
  private String address;
  
  private String tel;
  
  private String regDate;
  
  private int point;
  
  private int level;
  
  private int visited;
  
  public String getCusId() {
    return this.cusId;
  }
  
  public void setCusId(String cusId) {
    this.cusId = cusId;
  }
  
  public String getCusPw() {
    return this.cusPw;
  }
  
  public void setCusPw(String cusPw) {
    this.cusPw = cusPw;
  }
  
  public String getCusName() {
    return this.cusName;
  }
  
  public void setCusName(String cusName) {
    this.cusName = cusName;
  }
  
  public String getAddress() {
    return this.address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getTel() {
    return this.tel;
  }
  
  public void setTel(String tel) {
    this.tel = tel;
  }
  
  public String getRegDate() {
    return this.regDate;
  }
  
  public void setRegDate(String regDate) {
    this.regDate = regDate;
  }
  
  public int getPoint() {
    return this.point;
  }
  
  public void setPoint(int point) {
    this.point = point;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public void setLevel(int level) {
    this.level = level;
  }
  
  public int getVisited() {
    return this.visited;
  }
  
  public void setVisited(int visited) {
    this.visited = visited;
  }
}
