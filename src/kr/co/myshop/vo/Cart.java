package kr.co.myshop.vo;

import kr.co.myshop.vo.Cart;

public class Cart {
  private int cartNo;
  
  private String cusId;
  
  private int proNo;
  
  private int amount;
  
  public int getCartNo() {
    return this.cartNo;
  }
  
  public void setCartNo(int cartNo) {
    this.cartNo = cartNo;
  }
  
  public String getCusId() {
    return this.cusId;
  }
  
  public void setCusId(String cusId) {
    this.cusId = cusId;
  }
  
  public int getProNo() {
    return this.proNo;
  }
  
  public void setProNo(int proNo) {
    this.proNo = proNo;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public void setAmount(int amount) {
    this.amount = amount;
  }
}
