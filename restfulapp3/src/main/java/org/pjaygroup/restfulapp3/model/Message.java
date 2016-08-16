package org.pjaygroup.restfulapp3.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vijay Konduru
 *
 */
@XmlRootElement(name = "message")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Message {

	private String response_message;
	
	public Message() {
	}
	
	public Message(String response_message) {
		this.response_message = response_message;
	}

	public String getResponse_message() {
		return response_message;
	}

	public void setResponse_message(String response_message) {
		this.response_message = response_message;
	}

	@Override
	public String toString() {
		return "Message [response_message=" + response_message + "]";
	}

}
