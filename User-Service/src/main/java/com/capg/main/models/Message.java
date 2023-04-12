package com.capg.main.models;

import java.util.Date;

import lombok.Data;

@Data

public class Message {

    private String messageId;
    private String message;
    private Date messageDate;
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(String messageId, String message, Date messageDate) {
		super();
		this.messageId = messageId;
		this.message = message;
		this.messageDate = messageDate;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", message=" + message + ", messageDate=" + messageDate + "]";
	}

}