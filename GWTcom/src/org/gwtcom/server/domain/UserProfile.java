package org.gwtcom.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator( name="USERPROFILE_SEQ", sequenceName="userprofile_seq" )
@Table(name="userprofile")
public class UserProfile {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERPROFILE_SEQ")
	@Column(name="userprofile_id")
	private int _id;
	
	@Column(name="name")
	private String _name;
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		_id = id;
	}
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		_name = name;
	}
}
