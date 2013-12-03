package com.lsy.vehicle.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Seat
{

	private String	type;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
