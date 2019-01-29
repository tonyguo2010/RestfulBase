package com.seakie;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Component
public class Message {
	@ApiModelProperty(value = "Message Id", name = "ID", required = true)
	private int			id;
	@ApiModelProperty(value = "Message Name", name = "Name")
	private String 		name;
	@ApiModelProperty(value = "Message Value", name = "Value")
	private String		value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "{" + id + ", " + name + ", " + value + "}";
	}
	
	
}
