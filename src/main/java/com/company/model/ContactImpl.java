package com.company.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("contact")
public class ContactImpl implements Serializable, Contact {

	public String name;

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

}