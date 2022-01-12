package com.aipynux.community.entity;

/**
 * @author aipynux
 * @create 2022-01-11 11:10
 */
public class Page {
  /**
   * 当前页码 这个属性可以通过客户端改变
   */
  private int current = 1;
  /**
   * 页面上数据上限 这个属性可以通过客户端改变
   */
  private int limit = 10;
  /**
   * 数据总数（用于计算页面总数)
   */
  private int rows;
  /**
   * 查询路径（用于复用分页链接） ***
   */
  private String path;

  public int getCurrent() {
    return current;
  }

  public void setCurrent(int current) {
    if(current >= 1) {
      this.current = current;
    }
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    if(limit >= 1) {
      this.limit = limit;
    }
  }

  public int getRows() {
    return rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  /**
   * 获取当前页面第一个数据的位置以用来在数据库中查询(当前页的起始行)
   * @return
   */
  public int getOffSet(){
    return (current - 1) * limit;
  }

  /**
   * 获取页面总数
   * @return
   */
  public int getTotal(){
    return rows % limit == 0 ? rows/limit : rows/limit + 1;
  }

  /**
   * 获取可选页面最左值(起始页码）
   * @return
   */
  public int getFrom(){
    return current - 2 <= 1 ? 1 : current - 2;
  }

  /**
   * 获取可选页面最右值(结束页码)
   * @return
   */
  public int getTo(){
    int total = getTotal();
    return current + 2 >= total ? total : current + 2;
  }
}
