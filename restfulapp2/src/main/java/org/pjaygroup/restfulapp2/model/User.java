package org.pjaygroup.restfulapp2.model;

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
//@JsonIgnoreProperties(value="password", allowGetters=false, allowSetters=true)// Not working as expected
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String user_id;
	//@JsonProperty(access = Access.WRITE_ONLY) // Not working
//	@JsonIgnore // Xml part will not work well, json part works fine
//	private String password;

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

	@Override
	public String toString() {
		return "User [id=" + id + ", user_id=" + user_id + "]";
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
