package org.pjaygroup.restfulapp2.exceptions;

/**
 * @author Vijay Konduru
 *
 */
public class UserExistsException extends RuntimeException {

	private static final long serialVersionUID = 7058182342370221496L;

	public UserExistsException() {
		super();
	}

	public UserExistsException(String message) {
		super(message);
	}

}
