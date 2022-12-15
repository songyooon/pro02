package kr.co.myshop.vo;

import kr.co.myshop.vo.Sales;

public class Sales {
  private int saleNo;
  
  private String cusId;
  
  private String proNo;
  
  private int amount;
  
  private String saleDate;
  
  private int parselNo;
  
  private int salePayNo;
  
  private int parselState;

  
  public int getSaleNo() {
    return this.saleNo;
  }
  
  public void setSaleNo(int saleNo) {
    this.saleNo = saleNo;
  }
  
  public String getCusId() {
    return this.cusId;
  }
  
  public void setCusId(String cusId) {
    this.cusId = cusId;
  }
  
  public String getProNo() {
    return this.proNo;
  }
  
  public void setProNo(String proNo) {
    this.proNo = proNo;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public void setAmount(int amount) {
    this.amount = amount;
  }
  
  public String getSaleDate() {
    return this.saleDate;
  }
  
  public void setSaleDate(String saleDate) {
    this.saleDate = saleDate;
  }
  
  public int getParselNo() {
    return this.parselNo;
  }
  
  public void setParselNo(int parselNo) {
    this.parselNo = parselNo;
  }
  
  public int getSalePayNo() {
    return this.salePayNo;
  }
  
  public void setSalePayNo(int salePayNo) {
    this.salePayNo = salePayNo;
  }
  
  public int getParselState() {
    return this.parselState;
  }
  
  public void setParselState(int parselState) {
    this.parselState = parselState;
  }

  
  
  
}
