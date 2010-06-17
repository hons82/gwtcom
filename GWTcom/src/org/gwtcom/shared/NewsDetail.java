package org.gwtcom.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NewsDetail implements Serializable {
  private String id;
  private String displayName;
  
  public NewsDetail() {
    new NewsDetail("0", "");
  }

  public NewsDetail(String id, String displayName) {
    this.id = id;
    this.displayName = displayName;
  }
  
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getDisplayName() { return displayName; }
  public void setDisplayName(String displayName) { this.displayName = displayName; } 
}
