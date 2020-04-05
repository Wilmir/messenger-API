package com.wilmir.javabrains.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String errorMessage;
	private int errorCode;
	private String documenation;
	
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(String errorMessage, int errorCode, String documenation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documenation = documenation;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumenation() {
		return documenation;
	}
	public void setDocumenation(String documenation) {
		this.documenation = documenation;
	}
	
}
