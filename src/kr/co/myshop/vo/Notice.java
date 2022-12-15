package kr.co.myshop.vo;

import kr.co.myshop.vo.Notice;

public class Notice {
  private int notiNo;
  
  private String title;
  
  private String content;
  
  private String author;
  
  private String resDate;
  
  private int visited;
  
  public int getNotiNo() {
    return this.notiNo;
  }
  
  public void setNotiNo(int notiNo) {
    this.notiNo = notiNo;
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
  
  public String getResDate() {
    return this.resDate;
  }
  
  public void setResDate(String resDate) {
    this.resDate = resDate;
  }
  
  public int getVisited() {
    return this.visited;
  }
  
  public void setVisited(int visited) {
    this.visited = visited;
  }
}
