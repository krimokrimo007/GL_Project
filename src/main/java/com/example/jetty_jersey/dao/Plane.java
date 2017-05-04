package com.example.jetty_jersey.dao;

import com.example.jetty_jersey.db.CustomHashMap;

/*
 PUT plane/id
 {
 "planetype" : "exmaple of plane type"
 }
*/
public class Plane
{
	private final int planeId;
	private String planeType;

	public Plane(int planeId, String planeType)
	{
		this.planeId = planeId;
		this.planeType = planeType;
	}

	public int getPlaneId()
	{
		return planeId;
	}

	public String getPlaneType()
	{
		return planeType;
	}

	public void setPlaneType(String planeType)
	{
		this.planeType = planeType;
	}

	public CustomHashMap<String, String> toMap()
	{
		CustomHashMap<String, String> l = new CustomHashMap<String, String>();
		l.put("_id", String.valueOf(planeId));
		l.put("planeType;", planeType);
		return l;
	}

	@Override
	public String toString()
	{
		return "Plane(" + planeId + "," + planeType + ")";
	}

}
