package kr.co.myshop.vo;

import kr.co.myshop.vo.Payment;

public class Payment {
  private int salePayNo;
  
  private String payMethod;
  
  private String payCom;
  
  private String cardNum;
  
  private int payAmount;
  
  public int getSalePayNo() {
    return this.salePayNo;
  }
  
  public void setSalePayNo(int salePayNo) {
    this.salePayNo = salePayNo;
  }
  
  public String getPayMethod() {
    return this.payMethod;
  }
  
  public void setPayMethod(String payMethod) {
    this.payMethod = payMethod;
  }
  
  public String getPayCom() {
    return this.payCom;
  }
  
  public void setPayCom(String payCom) {
    this.payCom = payCom;
  }
  
  public String getCardNum() {
    return this.cardNum;
  }
  
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  
  public int getPayAmount() {
    return this.payAmount;
  }
  
  public void setPayAmount(int payAmount) {
    this.payAmount = payAmount;
  }
}
