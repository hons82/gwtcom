package org.gwtcom.client.panel.news;

public final class NewsItem {
	
	/**
	   * The sender's name.
	   */
	  public String sender;

	  /**
	   * The sender's email.
	   */
	  public String email;

	  /**
	   * The email subject line.
	   */
	  public String subject;

	  /**
	   * The email's HTML body.
	   */
	  public String body;

	  /**
	   * Read flag.
	   */
	  public boolean read;

	  public NewsItem(String sender, String email, String subject, String body) {
	    this.sender = sender;
	    this.email = email;
	    this.subject = subject;
	    this.body = body;
	  }

}
