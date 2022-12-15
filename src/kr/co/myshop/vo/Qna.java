package kr.co.myshop.vo;

import java.util.Date;
import kr.co.myshop.vo.Qna;

public class Qna {
  private int no;
  
  private String title;
  
  private String content;
  
  private String author;
  
  private Date resDate;
  
  private int lev;
  
  private int parno;
  
  private String sec;
  
  private int visited;
  
  public int getNo() {
    return this.no;
  }
  
  public void setNo(int no) {
    this.no = no;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public String getAuthor() {
    return this.author;
  }
  
  public void setAuthor(String author) {
    this.author = author;
  }
  
  public Date getResDate() {
    return this.resDate;
  }
  
  public void setResDate(Date resDate) {
    this.resDate = resDate;
  }
  
  public int getLev() {
    return this.lev;
  }
  
  public void setLev(int lev) {
    this.lev = lev;
  }
  
  public int getParno() {
    return this.parno;
  }
  
  public void setParno(int parno) {
    this.parno = parno;
  }
  
  public String getSec() {
    return this.sec;
  }
  
  public void setSec(String sec) {
    this.sec = sec;
  }
  
  public int getVisited() {
    return this.visited;
  }
  
  public void setVisited(int visited) {
    this.visited = visited;
  }
}
