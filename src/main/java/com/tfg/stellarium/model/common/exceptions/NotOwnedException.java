package com.tfg.stellarium.model.common.exceptions;

@SuppressWarnings("serial")
public class NotOwnedException extends Exception {

	private final String message;

	/**
		 * @param message
		 */
		public NotOwnedException() {
			super();
			this.message = "You do not own these stocks from this enterprise";
		}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}

}
