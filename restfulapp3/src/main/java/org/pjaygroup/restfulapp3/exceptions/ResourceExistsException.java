package org.pjaygroup.restfulapp3.exceptions;

/**
 * @author Vijay Konduru
 *
 */
public class ResourceExistsException extends RuntimeException {

	private static final long serialVersionUID = 7058182342370221496L;

	public ResourceExistsException() {
		super();
	}

	public ResourceExistsException(String message) {
		super(message);
	}

}
