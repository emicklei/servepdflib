package com.servepdf.dto;

import java.net.MalformedURLException;
import java.net.URL;

public class ValidationException extends Exception {
	private static final long serialVersionUID = 3064509405904488161L;

	public ValidationException(String field, String message) {
		super("invalid value for parameter ["+field +"] : "+ message);
	}
	
	public static void throwUnlessNonEmpty(String value, String field) throws ValidationException {
		throwIfNull(value, field);
		throwIfEmpty(value, field);
	}
	public static void throwUnlessOneOf(String value, String field, String[] allowedValues) throws ValidationException {
		final StringBuffer buf = new StringBuffer();
		buf.append("[");
		for (int i = 0; i < allowedValues.length; i++) {
			if (allowedValues[i].equals(value)) return;
			buf.append("\"");
			buf.append(allowedValues[i]);
			buf.append("\"");
			if (i != allowedValues.length-1) buf.append(",");
		}
		buf.append("]");
		throw new ValidationException(field, "value ["+value+"] is not one of " + buf.toString());
	}
	
	public static void throwIfNull(Object value, String field) throws ValidationException {
		if (value == null)
			throw new ValidationException(field, "value cannot be null");
	}
	public static void throwIfEmpty(String value, String field) throws ValidationException {		
		if (value.length() == 0)
			throw new ValidationException(field, "value cannot be empty");
	}
	public static void throwUnlessURL(String value, String field) throws ValidationException {		
		throwUnlessNonEmpty(value, field);
		if (!value.startsWith("http")) return;
		try {
			new URL(value);
		} catch (MalformedURLException e) {
			throw new ValidationException("field", "value ["+value+"] is not a valid URL:" + e.getMessage());
		}		
	}	
}
