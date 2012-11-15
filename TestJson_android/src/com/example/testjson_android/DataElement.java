package com.example.testjson_android;

public class DataElement {
	
	private String id;
	private String name;
	private String email;
	private String address;
	private String mobile;
	
	public DataElement(String id, String name, String email, String address, String moblie){
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.mobile = mobile;
	}
	public String getID(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getEmail(){
		return email;
	}
	public String getAddress(){
		return address;
	}
	public String getMoblie(){
		return mobile;
	}
	
}
