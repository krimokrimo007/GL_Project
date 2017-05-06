package com.example.jetty_jersey.dao;

import com.example.jetty_jersey.dao_interface.FlightDao;
import com.example.jetty_jersey.dao_interface.PlaneDao;
import com.example.jetty_jersey.dao_interface.TaskDao;
import com.example.jetty_jersey.dao_interface.TaskGenericDao;
import com.example.jetty_jersey.dao_implementation.*;

public class DAO
{
	private static TaskDao t;
	private static FlightDao f;
	private static PlaneDao p;
	private static TaskGenericDao tg;

	public static FlightDao getFlightDao()
	{
		if (f == null)
		{
			f = new FlightImpl();
		}
		return f;
	}

	public static TaskDao getTaskDao()
	{
		if (t == null)
		{
			t = new TaskImpl();
		}
		return t;
	}

	public static TaskGenericDao getTaskGenericDao()
	{
		if (tg == null)
		{
			tg = new TaskGenericImpl();
		}
		return tg;
	}

	public static PlaneDao getPlaneDao()
	{
		if (p == null)
		{
			p = new PlaneImpl();
		}
		return p;
	}

}
