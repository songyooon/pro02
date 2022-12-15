package kr.co.myshop.vo;

import kr.co.myshop.vo.Product;

public class Product {
  private int proNo;
  
  private int cateNo;
  
  private String proName;
  
  private String proSpec;
  
  private int oriPrice;
  
  private double discountRate;
  
  private int proPrice;
  
  private String proPic;
  
  private String proPic2;
  
  private int amount;
  
  public int getProNo() {
    return this.proNo;
  }
  
  public void setProNo(int proNo) {
    this.proNo = proNo;
  }
  
  public int getCateNo() {
    return this.cateNo;
  }
  
  public void setCateNo(int cateNo) {
    this.cateNo = cateNo;
  }
  
  public String getProName() {
    return this.proName;
  }
  
  public void setProName(String proName) {
    this.proName = proName;
  }
  
  public String getProSpec() {
    return this.proSpec;
  }
  
  public void setProSpec(String proSpec) {
    this.proSpec = proSpec;
  }
  
  public String getProPic() {
    return this.proPic;
  }
  
  public void setProPic(String proPic) {
    this.proPic = proPic;
  }
  
  public String getProPic2() {
    return this.proPic2;
  }
  
  public void setProPic2(String proPic2) {
    this.proPic2 = proPic2;
  }
  
  public int getOriPrice() {
    return this.oriPrice;
  }
  
  public void setOriPrice(int oriPrice) {
    this.oriPrice = oriPrice;
  }
  
  public double getDiscountRate() {
    return this.discountRate;
  }
  
  public void setDiscountRate(double discountRate) {
    this.discountRate = discountRate;
  }
  
  public int getProPrice() {
    return (int)(this.oriPrice * (1.0D - this.discountRate));
  }
  
  public void setProPrice() {
    this.proPrice = (int)(this.oriPrice * (1.0D - this.discountRate));
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public void setAmount(int amount) {
    this.amount = amount;
  }
}
