package org.pjaygroup.restfulapp1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vijay Konduru
 *
 */
@Entity
@Table(name = "user")
@XmlRootElement(name = "user")
//@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String user_id;
//	@JsonIgnore // Xml part will not work well, json part works fine
//	@XmlTransient // Not working as expected
//	private String password;
	private String first_name;
	private String last_name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ "]";
	}

/*	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}*/
	
	
}
