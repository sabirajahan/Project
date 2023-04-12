package com.capg.main.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
@Document("orders")
public class Orders {
	@Id
	private String oid;
	@Field
	private String date;
	@Field
	private String customerName;
	@Field
	private String customerAddress;	
	@Field
	private String prname;
	@Field
	private double prprice;
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orders(String oid, String date, String customerName, String customerAddress, String prname, double prprice) {
		super();
		this.oid = oid;
		this.date = date;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.prname = prname;
		this.prprice = prprice;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getPrname() {
		return prname;
	}
	public void setPrname(String prname) {
		this.prname = prname;
	}
	public double getPrprice() {
		return prprice;
	}
	public void setPrprice(double prprice) {
		this.prprice = prprice;
	}
	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", date=" + date + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", prname=" + prname + ", prprice=" + prprice + "]";
	}
	
	
	
}